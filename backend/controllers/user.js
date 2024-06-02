const userService = require('../services/user');
const generationService = require('../services/generation');

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
            const userData = req.user;
            const history = await generationService.userHistory(userData.id);
            res.json(history);
        }
        catch(error) {
            next(error);
        }
    }

    async jobs(req, res, next)  {
        try {
            const userData = req.user;
            const jobs = await generationService.userJobs(userData.id);
            res.json(jobs);
        }
        catch(error) {
            next(error);
        }
    }

    async getGeneration(req, res, next) {
        try {
            const generationId = req.params.id;
            const generation = await generationService.getGeneration(generationId);
            res.json(generation);
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