from fastapi import FastAPI, File, UploadFile
from fastapi.responses import JSONResponse
from PIL import Image
import torch
import io
import numpy as np
from diffusers import StableDiffusionPipeline
from segment_anything import SamPredictor, sam_model_registry
import base64

app = FastAPI()

# Загружаем модель YOLO
yolo_model = torch.hub.load('ultralytics/yolov5', 'custom', path='weights/best.pt', force_reload=True)

# Загружаем модель SAM
sam_model_type = "default"
sam_model = sam_model_registry[sam_model_type](pretrained=True)
sam_predictor = SamPredictor(sam_model)

# Загружаем модель Stable Diffusion
model_id = "CompVis/stable-diffusion-v1-4"
device = "cuda" if torch.cuda.is_available() else "cpu"
print(device)
pipe = StableDiffusionPipeline.from_pretrained(model_id)
pipe = pipe.to(device)

# Промпты для Stable Diffusion
prompt = "room, (photorealistic:1.2)"
negative_prompt = "(semi-realistic, cgi, 3d, render, sketch, cartoon, drawing, anime), text, cropped, out of frame, low quality, jpeg artifacts"

@app.post("/generate")
async def process_image(file: UploadFile = File(...)):
    # Читаем изображение из form-data
    image = Image.open(file.file).convert("RGB")
    
    # Преобразование изображения в формат, который понимает YOLO
    img_np = np.array(image)
    results = yolo_model(img_np)

    # Список для хранения сгенерированных изображений
    generated_images = []

    for det in results.xyxy[0]:
        xmin, ymin, xmax, ymax, confidence, cls = det

        # Извлекаем объект из изображения
        obj_image = img_np[int(ymin):int(ymax), int(xmin):int(xmax)]

        # Используем SAM для сегментации объекта внутри бокса
        sam_predictor.set_image(obj_image)
        masks = sam_predictor.predict()

        # Применяем Stable Diffusion для генерации новых изображений на основе масок
        for mask in masks:
            mask_np = mask.cpu().numpy().astype(np.uint8) * 255
            mask_img = Image.fromarray(mask_np)

            generated_imgs = pipe(prompt, negative_prompt=negative_prompt, init_image=mask_img, strength=0.75, guidance_scale=7.5).images

            # Конвертируем изображения в base64
            for img in generated_imgs:
                buffered = io.BytesIO()
                img.save(buffered, format="PNG")
                img_str = base64.b64encode(buffered.getvalue()).decode("utf-8")
                generated_images.append(img_str)

    return JSONResponse(content={"images": generated_images})

if __name__ == "__main__":
    import uvicorn
    uvicorn.run(app, host="0.0.0.0", port=3000)
