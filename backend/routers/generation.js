const express = require('express');
const PrivateAuthMiddleware = require('../middlewares/privateAuth');
const PublicAuthMiddleware = require('../middlewares/publicAuth');
const generationController = require('../controllers/generation');
const { save, saveFileNameMiddleware } = require('../middlewares/saveFile');

const generationRouter = express.Router();

generationRouter.post(
    '/private',
    save.single('image'), // Используем только save.single для загрузки и сохранения
    saveFileNameMiddleware,
    PrivateAuthMiddleware,
    generationController.create
);

generationRouter.post(
    '/public',
    save.single('image'), // Используем только save.single для загрузки и сохранения
    saveFileNameMiddleware,
    PublicAuthMiddleware,
    generationController.create
);

generationRouter.get(
    '/private/mods',
    PrivateAuthMiddleware,
    generationController.getMods
);

generationRouter.get(
    '/public/mods',
    PublicAuthMiddleware,
    generationController.getMods
);

module.exports = generationRouter;
