const generationService = require('../services/generation');
const ApiError = require('../exceptions/api');

class generationController {

    async createPrivate(req, res, next) {
        try {
            const userData = req.user;
            const prompt = req.body.prompt;
            console.log(prompt);
            const file = req.file;

            if(!file) {
                throw ApiError.BadRequestError('Не загружен файл в форме');
            }
            console.log(0);
            const newGeneration = await generationService.createGeneration(userData, prompt, file);

            res.json(newGeneration);
            console.log(1);
            await delay(30000);
            console.log(newGeneration);
            await generationService.completeGeneration(newGeneration._id);
        }
        catch(error) {
            next(error);
        }
    }

}

module.exports = new generationController();

function delay(ms) {
    return new Promise((resolve) => {
        setTimeout(resolve, ms);
    });
}  