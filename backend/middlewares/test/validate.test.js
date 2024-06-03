const ValidateMiddleware = require('../validate');
const ApiError = require('../../exceptions/api');

describe('Validate Middleware', () => {

    describe('Валидация на пустое поле', () => {
        it('Ошибка, если поле пустое', () => {
            const req = { body: { field1: '', field2: 'value' } };
            const res = {};
            const next = jest.fn();
            ValidateMiddleware.isEmpty(['field1', 'field2'], ['Field 1', 'Field 2'])(req, res, next);
            expect(next).toHaveBeenCalledWith(expect.any(ApiError));
            expect(next.mock.calls[0][0].message).toBe('Поле Field 1 не должно быть пустым');
        });

        it('Нет ошибки, если поле не пустое', () => {
            const req = { body: { field1: 'value1', field2: 'value2' } };
            const res = {};
            const next = jest.fn();
            ValidateMiddleware.isEmpty(['field1', 'field2'], ['Field 1', 'Field 2'])(req, res, next);
            expect(next).toHaveBeenCalled();
            expect(next.mock.calls[0][0]).toBeUndefined();
        });
    });

    describe('Валидация Email', () => {
        it('Ошибка, если невалидный email', () => {
            const req = { body: { email: 'invalidEmail' } };
            const res = {};
            const next = jest.fn();
            ValidateMiddleware.isEmail('email')(req, res, next);
            expect(next).toHaveBeenCalledWith(expect.any(ApiError));
            expect(next.mock.calls[0][0].message).toBe('Неправильный формат почты');
        });

        it('Нет ошибки, если email валидный', () => {
            const req = { body: { email: 'test@example.com' } };
            const res = {};
            const next = jest.fn();
            ValidateMiddleware.isEmail('email')(req, res, next);
            expect(next).toHaveBeenCalled();
            expect(next.mock.calls[0][0]).toBeUndefined();
        });
    });

    describe('Валидация на строку в диапазоне длины', () => {
        it('Ошибка, если поле не является строкой', () => {
            const req = { body: { username: 12345 } };
            const res = {};
            const next = jest.fn();
            ValidateMiddleware.isInRange('username', 'Username', 3, 10)(req, res, next);
            expect(next).toHaveBeenCalledWith(expect.any(ApiError));
            expect(next.mock.calls[0][0].message).toBe('Username не должно быть числом');
        });

        it('Ошибка, если длина поля выходит за пределы диапазона', () => {
            const req = { body: { username: 'ab' } };
            const res = {};
            const next = jest.fn();
            ValidateMiddleware.isInRange('username', 'Username', 3, 10)(req, res, next);
            expect(next).toHaveBeenCalledWith(expect.any(ApiError));
            expect(next.mock.calls[0][0].message).toBe('Username должен быть длиной от 3 до 10 символов');
        });

        it('Нет ошибки, если длина поля находится в диапазоне', () => {
            const req = { body: { username: 'validUser' } };
            const res = {};
            const next = jest.fn();
            ValidateMiddleware.isInRange('username', 'Username', 3, 10)(req, res, next);
            expect(next).toHaveBeenCalled();
            expect(next.mock.calls[0][0]).toBeUndefined();
        });
    });
});
