# Используем базовый образ Python
FROM python:3.9-slim

# Устанавливаем зависимости
WORKDIR /app
COPY requirements.txt .
RUN pip install --no-cache-dir -r requirements.txt
# Копируем код приложения
COPY . .

# Запускаем приложение
CMD ["uvicorn", "app:app", "--host", "0.0.0.0", "--port", "3000"]