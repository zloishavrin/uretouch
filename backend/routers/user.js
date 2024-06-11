const Router = require('express');
const PrivateAuthMiddleware = require('../middlewares/privateAuth');
const PublicAuthMiddleware = require('../middlewares/publicAuth');
const UserController = require('../controllers/user');

const userRouter = Router();

userRouter.get(
    '/info',
    PrivateAuthMiddleware,
    UserController.info
);

userRouter.get(
    '/history',
    PrivateAuthMiddleware,
    UserController.history
);

userRouter.get(
    '/jobs',
    PrivateAuthMiddleware,
    UserController.jobs
)

userRouter.get(
    '/generation/:id',
    PrivateAuthMiddleware,
    UserController.getGeneration
);

userRouter.get(
    '/generation/public/:id',
    PublicAuthMiddleware,
    UserController.getGeneration
)

userRouter.get(
    '/generations',
    PrivateAuthMiddleware,
    UserController.getGenerations
)

userRouter.get(
    '/api-key',
    PrivateAuthMiddleware,
    UserController.apiKey
);

module.exports = userRouter;