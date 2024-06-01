const Router = require('express');
const PrivateAuthMiddleware = require('../middlewares/privateAuth');
const generationController = require('../controllers/generation');
const multer = require('multer');

const generationRouter = Router();
const upload = multer();

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