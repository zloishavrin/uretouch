const ApiError = require('../exceptions/api');
const userModel = require('../models/user');

module.exports = async (req, res, next) => {
    try {
        const authorizationHeader = req.headers.authorization;
        if(!authorizationHeader) {
            return next(ApiError.UnauthorizedError());
        }

        const apiKey  = authorizationHeader.split(' ')[1];
        if(!apiKey) {
            return next(ApiError.UnauthorizedError());
        }

        const userData = await userModel.findOne({apiKey});
        if(!userData) {
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