const errorHandlerMiddleware = require('../error');
const ApiError = require('../../exceptions/api');

describe('Error Handler Middleware', () => {
    let req, res, next;

    beforeEach(() => {
        req = {};
        res = {
            status: jest.fn().mockReturnThis(),
            json: jest.fn()
        };
        next = jest.fn();
        console.log = jest.fn(); // Mock console.log
    });

    it('Корректная обработка ApiError', () => {
        const error = new ApiError(400, "Test ApiError");

        errorHandlerMiddleware(error, req, res, next);

        expect(console.log).toHaveBeenCalledWith(error);
        expect(res.status).toHaveBeenCalledWith(400);
        expect(res.json).toHaveBeenCalledWith({ message: 'Test ApiError' });
    });

    it('Корректная обработка общих ошибок', () => {
        const error = new Error('Test Error');

        errorHandlerMiddleware(error, req, res, next);

        expect(console.log).toHaveBeenCalledWith(error);
        expect(res.status).toHaveBeenCalledWith(500);
        expect(res.json).toHaveBeenCalledWith({ message: 'Непредвиденная ошибка' });
    });
});
