const ApiError = require('../exceptions/api');
const tokenService = require('../services/token');

module.exports = function (req, res, next) {
    try {
        
        const authorizationHeader = req.headers.authorization;
        if(!authorizationHeader) {
            return next(ApiError.UnauthorizedError());
        }

        const accessToken  = authorizationHeader.split(' ')[1];
        if(!accessToken) {
            return next(ApiError.UnauthorizedError());
        }

        const userData = tokenService.validateAccessToken(accessToken);
        if(!userData)  {
            return next(ApiError.UnauthorizedError());
        }

        if(!userData.isActivated) {
            return next(ApiError.BadRequestError('Аккаунт не подтвержден по электронной почте'))
        }

        req.user = userData;
        next();

    }
    catch(error) {
        return next(ApiError.UnauthorizedError());
    }
}