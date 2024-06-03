const authMiddleware = require('../privateAuth');
const ApiError = require('../../exceptions/api');
const tokenService = require('../../services/token');

jest.mock('../../services/token');

describe('Private Authentification Middleware', () => {
    let req, res, next;

    beforeEach(() => {
        req = {
            headers: {}
        };
        res = {};
        next = jest.fn();
    });

    it('Не аутентифицирован, если заголовок пустой', () => {
        authMiddleware(req, res, next);

        expect(next).toHaveBeenCalledWith(expect.any(ApiError));
        expect(next.mock.calls[0][0].message).toBe('Пользователь не аутентифицирован');
    });

    it('Не аутентифицирован, если в заголовке нет токена', () => {
        req.headers.authorization = 'Bearer ';

        authMiddleware(req, res, next);

        expect(next).toHaveBeenCalledWith(expect.any(ApiError));
        expect(next.mock.calls[0][0].message).toBe('Пользователь не аутентифицирован');
    });

    it('Не аутентифицирован, если токен не валидный', () => {
        req.headers.authorization = 'Bearer invalidToken';
        tokenService.validateAccessToken.mockReturnValue(null);

        authMiddleware(req, res, next);

        expect(next).toHaveBeenCalledWith(expect.any(ApiError));
        expect(next.mock.calls[0][0].message).toBe('Пользователь не аутентифицирован');
    });

    it('Ошибка, если аккаунт не активирован', () => {
        req.headers.authorization = 'Bearer validToken';
        tokenService.validateAccessToken.mockReturnValue({ isActivated: false });

        authMiddleware(req, res, next);

        expect(next).toHaveBeenCalledWith(expect.any(ApiError));
        expect(next.mock.calls[0][0].message).toBe('Аккаунт не подтвержден по электронной почте');
    });

    it('Нет ошибки, если токен валидный и аккаунт активирован', () => {
        req.headers.authorization = 'Bearer validToken';
        const userData = { isActivated: true };
        tokenService.validateAccessToken.mockReturnValue(userData);

        authMiddleware(req, res, next);

        expect(req.user).toBe(userData);
        expect(next).toHaveBeenCalled();
        expect(next.mock.calls[0][0]).toBeUndefined();
    });
});