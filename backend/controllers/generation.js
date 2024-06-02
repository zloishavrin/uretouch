const generationService = require('../services/generation');
const ApiError = require('../exceptions/api');

class generationController {

    async createPrivate(req, res, next) {
        try {
            const userData = req.user;
            const prompt = req.body.prompt;
            const file = req.file;
            if(!file) {
                throw ApiError.BadRequestError('Не загружен файл в форме');
            }
            else if(!prompt) {
                throw ApiError.BadRequestError('Необходимо ввести промпт');
            }
            const newGeneration = await generationService.createGeneration(userData, prompt, file);

            res.json({ generation_id: newGeneration._id });
            await delay(30000);
            await generationService.completeGeneration(newGeneration._id);
        }
        catch(error) {
            next(error);
        }
    }

}

module.exports = new generationController();


// MOCK-ФУНКЦИЯ
function delay(ms) {
    return new Promise((resolve) => {
        setTimeout(resolve, ms);
    });
}  