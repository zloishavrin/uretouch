const Router = require('express');
const PrivateAuthMiddleware = require('../middlewares/privateAuth');

const generationRouter = Router();

generationRouter.post(
    '/private',
    PrivateAuthMiddleware,
);

generationRouter.post(
    '/public',
);

module.exports = generationRouter;