const ApiError = require('../exceptions/api');

module.exports = class ValidateMiddleware {

    static isEmpty(fields, fieldNames) {
        return (req, res, next) => {
            const values = fields.map(field => req.body[field]);
            for (let i = 0; i < values.length; i++) {
                if (!values[i]) {
                    return next(ApiError.BadRequestError(`Поле ${fieldNames[i]} не должно быть пустым`));
                }
            }
            next();
        };
    }

    static isEmail(field) {
        return (req, res, next) => {
            const value = req.body[field];
            const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            if (!emailRegex.test(value)) {
                return next(ApiError.BadRequestError('Неправильный формат почты'));
            }
            next();
        };
    }

    static isInRange(field, fieldName, min, max) {
        return (req, res, next) => {
            const value = req.body[field];
            if (typeof value != 'string') {
                return next(ApiError.BadRequestError(`${fieldName} не должно быть числом`));
            }
            if (value.length <  min  || value.length  >  max)  {
                return next(ApiError.BadRequestError(`${fieldName} должен быть длиной от ${min} до ${max} символов`));
            }
            next();
        };
    }

}
