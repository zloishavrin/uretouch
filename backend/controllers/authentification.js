const userService = require('../services/user');

class AuthentificationController {

    async registration(req, res, next) {
        try {
            const { email, password } = req.body;
            const userData = await userService.registration(email, password);
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
            const { email, password }  = req.body;
            const userData = await userService.login(email, password);
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

    async isActivated(req, res, next) {
        try {
            const userId = req.params.userId;
            const isActivated  = await userService.isActivated(userId);
            return res.json({ status: isActivated});
        }
        catch(error) {
            next(error);
        }
    }

}

module.exports = new AuthentificationController();