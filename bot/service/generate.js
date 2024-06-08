const axios = require('axios');
const FormData = require('form-data');
const fs = require('fs');

class GenerateService {

    async getMods() {
        const response = await axios.get(
            "https://uretouch.shaligula.ru/api/generation/public/mods", {
                headers: { 
                    "Authorization": `Bearer ${process.env.BOT_SERVICE_API_KEY}`
                } 
            }
        );
        return response.data;
    };

    async startGeneration(prompt, mode, fileUrl) {
        const photoResponse = await axios({
            url: fileUrl,
            responseType: 'stream'
        });

        const localFilePath = './photo/photo.jpg';
        const writer = fs.createWriteStream(localFilePath);

        photoResponse.data.pipe(writer);

        await new Promise((resolve, reject) => {
            writer.on('finish', resolve);
            writer.on('error', reject);
        });

        const form = new FormData();
        if (prompt) {
            form.append('prompt', prompt);
        } else if (mode) {
            form.append('mode', mode);
        }
        form.append('image', fs.createReadStream(localFilePath));

        const response = await axios.request({
            method: 'POST',
            url: 'https://uretouch.shaligula.ru/api/generation/public',
            headers: {
                "Authorization": `Bearer ${process.env.BOT_SERVICE_API_KEY}`,
                ...form.getHeaders()
            },
            data: form
        });
        fs.unlinkSync(localFilePath);
        return response.data.generation_id;
    }

}

module.exports = new GenerateService();