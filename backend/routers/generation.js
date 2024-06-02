const Router = require('express');
const PrivateAuthMiddleware = require('../middlewares/privateAuth');
const generationController = require('../controllers/generation');
const multer = require('multer');

const generationRouter = Router();

const storage = multer.memoryStorage();
const upload = multer({ 
    storage: storage,
    limits: { fileSize: 100 * 1024 * 1024 }  // Устанавливаем лимит на размер файла: 50MB
});

generationRouter.post(
    '/private',
    upload.single('image'),
    PrivateAuthMiddleware,
    generationController.createPrivate
);

generationRouter.post(
    '/public',
);

module.exports = generationRouter;