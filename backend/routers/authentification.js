const Router = require('express');
const ValidateMiddleware = require('../middlewares/validate');
const AuthentificationController = require('../controllers/authentification');

const authentificationRouter = Router();

authentificationRouter.post(
    '/registration',
    ValidateMiddleware.isEmpty(
        ['login', 'email', 'password'], 
        ['"Логин"', '"Почта"', '"Пароль"']
    ),
    ValidateMiddleware.isEmail('email'),
    ValidateMiddleware.isInRange('login', 6, 32),
    ValidateMiddleware.isInRange('password', 6, 32),
    AuthentificationController.registration
);

authentificationRouter.post(
    '/login',
    ValidateMiddleware.isEmpty(
        ['login',  'password'],  
        ['"Логин"',  '"Пароль"']
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

authentificationRouter.get(
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

module.exports = authentificationRouter;