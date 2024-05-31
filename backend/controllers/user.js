const userService = require('../services/user');

class userController {

    async info(req, res, next) {
        try {
            const userData = req.user;
            res.json(userData);
        }
        catch(error) {
            next(error);
        }
    }

    async history(req, res, next) {
        try {

        }
        catch(error) {
            next(error);
        }
    }

    async apiKey(req, res, next)  {
        try {
            const apiKey = await userService.getAPIKey(req.user.id);
            res.json({ apiKey });
        }
        catch(error) {
            next(error);
        }
    }

}

module.exports = new userController();