const generationService = require('../services/generation');
const ApiError = require('../exceptions/api');
const translateSerice = require('../services/translate');

class generationController {

    async create(req, res, next) {
        try {
            const userData = req.user;
            const mode = req.body.mode;
            const file = req.file;
            let prompt = req.body.prompt;

            if(!file) {
                throw ApiError.BadRequestError('Не загружен файл в форме');
            }
            else if(!prompt && !mode) {
                throw ApiError.BadRequestError('Необходимо ввести промпт или выбрать режим');
            }
            else if(!file.mimetype.startsWith('image/'))  {
                throw ApiError.BadRequestError('Файл должен быть изображением');
            }
            else if(!prompt) {
                try {
                    prompt = await generationService.getMode(mode);
                }
                catch(error)  {
                    throw ApiError.BadRequestError('Такого режима не существует');
                }
            }
            else if(!mode) {
                try {
                    prompt = await translateSerice.translate(prompt);
                }
                catch(error) {
                    console.log(error);
                    throw ApiError.InternalServerError('Ошибка перевода промпта');
                }
            }

            console.log(prompt);

            const newGeneration = await generationService.createGeneration(userData, prompt, file);

            res.json({ generation_id: newGeneration._id });
            await delay(30000);
            await generationService.completeGeneration(newGeneration._id);
        }
        catch(error) {
            next(error);
        }
    }

    async getMods(req, res, next) {
        try {
            const mods = await generationService.getMods();
            res.json(mods);
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