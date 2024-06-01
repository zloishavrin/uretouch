# U-Retouch

Платформа для визуализации товара на релевантных фонах

## Запуск платформы

Сервисы объединены в Docker-композицию. Для запуска понадобится Docker Dekstop (Windows, MacOS) или Docker + Docker-Compose (Linux).

Запуск выполняется следующей командой:

```bash
docker-compose up --build
```

Для того, чтобы выключить необходимо нажать CTRL+C, а затем ввести:

```bash
docker-compose down
```

### Локальные переменные

Перед запуском необходимо создать в корне проекта ENV-файл (или установить вручную переменные окружения).

|Переменная|Обозначение|
|-|-|
|MONGO_ROOT_USER|Пользователь для базы данных|
|MONGO_ROOT_PASSWORD|Пароль для пользователя базы данных|
|MONGOEXPRESS_LOGIN|Пользователь для панели управления базой данных|
|MONGOEXPRESS_PASSWORD|Пароль для пользователя панели управления базой данных|
|API_URL|URL для доступа к API|
|CLIENT_URL|URL для доступа к клиенту|
|JWT_ACCESS_SECRET|Секретный ключ для токенов доступа JWT-аутентификации|
|JWT_REFRESH_SECRET|Секретный ключ для токенов обновления JWT-аутентификации|
|SMTP_SERVICE|Название сервиса для SMTP-протокола|
|SMTP_USER|Пользователь для SMTP-сервиса|
|SMTP_PASSWORD|Пароль пользователя SMTP-сервиса|

Пример ENV-файла:

```
MONGO_ROOT_USER=admin
MONGO_ROOT_PASSWORD=admin
MONGOEXPRESS_LOGIN=admin
MONGOEXPRESS_PASSWORD=admin
API_URL='http://localhost:3001/'
CLIENT_URL='http://localhost/'
JWT_ACCESS_SECRET='example1'
JWT_REFRESH_SECRET='example2'
SMTP_SERVICE='gmail'
SMTP_USER='example@gmail.com'
SMTP_PASSWORD='exam plee xamp leex'
```

## Frontend

React-проект находится в директиве **client** и запускается на порту 3000 на localhost.

Для установки зависимостей необходимо прописать зависимость в package.json и перезапустить Docker-композицию. Устанавливать в node_modules ничего не надо.

Зависимости, которые есть в проекте на данный момент:

|Зависимость|Назначение|
|-|-|
|axios|Запросы|
|mobx|Стейт-менеджер|
|mobx-react-lite|Работа со стейт-менеджером из React|
|react-hook-form|Работа с формами|
|react-router-dom|Работа с роутингом|

Чтобы посмотреть, как будет выглядеть готовый билд, можно закинуть файлы билда в **static_client** и открыть localhost на HTTP или HTTPS порту.

## Backend

Находится в директиве **backend** и запускается на порту 3001 на localhost.

К серверу можно обратиться через **localhost/api/** по HTTP-порту.

Например, с фронтенда (веб):
```javascript
useEffect(() => {
axios.post('http://localhost/api/auth/login')
    .then(response => {
        console.log(response.data);
    })
    .catch(error => {
        console.error(error.response.data.message);
    });
}, []);
```

### Спецификация

Спецификацию можно открыть по localhost/swagger/ по HTTP или HTTPS-порту.

Также спецификация доступна [здесь](https://uretouch.shaligula.ru/swagger/).

## Панель управления базой данных

Можно посмотреть, что происходит в базе данных в панели управления. Она доступна на localhost на порту **8888**.

### Доступ к Docker Hub

Для решения проблемы блокировки Docker Hub в России следует изменить daemon.json файл докера. Пример настройки зеркала в файле daemon.json