const Router = require('express');
const PrivateAuthMiddleware = require('../middlewares/privateAuth');
const generationController = require('../controllers/generation');
const multer = require('multer');

const generationRouter = Router();

const storage = multer.memoryStorage();
const upload = multer({ 
    storage: storage,
    limits: {
        fileSize: 10 * 1024 * 1024,
        fieldSize: 10 * 1024 * 1024
    }
});

generationRouter.post(
    '/private',
    upload.single('image'),
    PrivateAuthMiddleware,
    generationController.create
);

generationRouter.post(
    '/public',
    upload.single('image'),
    PublicAuthMiddleware,
    generationController.create
);

generationRouter.get(
    '/private/mods',
    PrivateAuthMiddleware,
    generationController.getMods
)

generationRouter.get(
    '/public/mods',
    PublicAuthMiddleware,
    generationController.getMods
);

module.exports = generationRouter;