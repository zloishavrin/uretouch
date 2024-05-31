const userService = require('../services/user');

class AuthentificationController {

    async registration(req, res, next) {
        try {
            const { login, email, password } = req.body;
            const userData = await userService.registration(login, email, password);
            res.cookie('refreshToken', userData.refreshToken, {maxAge: 30 * 24 * 60 * 60 * 1000, httpOnly: true});
            return res.json(userData);
        }
        catch(error) {
            next(error);
        }
    }

    async activate(req, res, next)  {
        try {
            const activationLink = req.params.link;
            await userService.activate(activationLink);
            return res.redirect(process.env.CLIENT_URL);
        }
        catch(error)  {
            next(error);
        }
    }

    async login(req, res, next)  {
        try {
            const { login, password }  = req.body;
            const userData = await userService.login(login, password);
            res.cookie('refreshToken', userData.refreshToken, {maxAge: 30 * 24 * 60 * 60 * 1000, httpOnly: true});
            return res.json(userData);
        }
        catch(error) {
            next(error);
        }
    }

    async refresh(req, res, next) {
        try {
            const { refreshToken } = req.body;
            const userData = await userService.refresh(refreshToken);
            res.cookie('refreshToken', userData.refreshToken, {maxAge: 30 * 24 * 60 * 60 * 1000, httpOnly: true});
            return res.json(userData);
        }
        catch(error) {
            next(error);
        }
    }

    async logout(req, res, next) {
        try {
            const { refreshToken } = req.body;
            const token = await userService.logout(refreshToken);
            res.clearCookie('refreshToken');
            return res.json({message: "Успешный выход"});
        }
        catch(error) {
            next(error);
        }
    }

}

module.exports = new AuthentificationController();