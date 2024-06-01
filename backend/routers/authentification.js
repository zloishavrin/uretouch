const Router = require('express');
const ValidateMiddleware = require('../middlewares/validate');
const AuthentificationController = require('../controllers/authentification');

const authentificationRouter = Router();

authentificationRouter.post(
    '/registration',
    ValidateMiddleware.isEmpty(
        ['email', 'password'], 
        ['"Почта"', '"Пароль"']
    ),
    ValidateMiddleware.isEmail('email'),
    ValidateMiddleware.isInRange('password', 6, 32),
    AuthentificationController.registration
);

authentificationRouter.post(
    '/login',
    ValidateMiddleware.isEmail('email'),
    ValidateMiddleware.isEmpty(
        ['password'],  
        ['"Пароль"']
    ),
    AuthentificationController.login
);

authentificationRouter.post(
    '/logout',
    ValidateMiddleware.isEmpty(
        ['refreshToken'], 
        ['"refreshToken"']
    ),
    AuthentificationController.logout
)

authentificationRouter.post(
    '/refresh',
    ValidateMiddleware.isEmpty(
        ['refreshToken'], 
        ['"refreshToken"']
    ),
    AuthentificationController.refresh
);

authentificationRouter.get(
    '/activate/:link',
    AuthentificationController.activate
);

authentificationRouter.get(
    '/isActivated/:userId',
    AuthentificationController.isActivated
)

module.exports = authentificationRouter;