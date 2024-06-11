const TelegramBot = require('node-telegram-bot-api');
const userService = require('./user');
const generateService = require('./generate');

const bot = new TelegramBot(process.env.BOT_TOKEN, {
    polling: true
});

const startBot = () => {

    bot.on('message', async (msg) => {
        try {
            const chatId = msg.chat.id;
            const user = await userService.getUser(chatId);
            if (!user) {
                await userService.createUser(chatId);
            }

            if(msg.text === '/start')  {
                await userService.changeStep(chatId, 'start');
                await bot.sendMessage(chatId,  'Вас приветствует команда <b>U-Retouch!</b>\n\nЧто умеет этот бот? Визуализировать товары на релевантных фонах!\n\nДля того, чтобы воспользоваться ботом отправьте изображение с товаром в диалог!', {
                    parse_mode: 'HTML'
                });
            }
        }
        catch(error) {
            console.log(error);
        }
    });

    bot.on('photo', async (msg) => {
        try {
            const chatId = msg.chat.id;
            const photoId = msg.photo[msg.photo.length-1].file_id;
            const user = await userService.getUser(chatId);
            if (!user) {
                await userService.createUser(chatId);
            }
            
            const mods = await generateService.getMods();
            const buttons = mods.map(mode => [{ text: mode.name, callback_data: `mode_${mode._id}`}]);

            await bot.sendPhoto(chatId, photoId, {
                parse_mode: 'HTML',
                caption: '<b>Выберите режим для генерации</b>',
                reply_markup: {
                    inline_keyboard: buttons
                }
            });
        }
        catch(error) {
            console.log(error);
        }
    });

    bot.on('callback_query', async (msg) => {
        try {
            const chatId = msg.from.id;
            const callbackData = msg.data;
            const user = await userService.getUser(chatId);
            if (!user) {
                await userService.createUser(chatId);
            }

            if(callbackData.startsWith('mode_')) {
                const modeId  = callbackData.replace('mode_', '');
                const photoId  = msg.message.photo[msg.message.photo.length-1].file_id;
                const photoUrl = await bot.getFileLink(photoId);
                const generationData = await generateService.startGeneration(null, modeId, photoUrl);
                const waitMessage = await bot.sendMessage(chatId, 'Пожалуйста, подождите...');
                while(true) {
                    const generation = await generateService.genGeneration(generationData);
                    if(generation.status == "completed") {
                        const photoGroup = [];
                        for(let index = 0; index < generation.url.length; index++) {
                            photoGroup.push({
                                type: "photo",
                                media: generation.url[index],
                            })
                        }
                        await bot.deleteMessage(chatId, waitMessage.message_id);
                        await bot.sendMediaGroup(chatId, photoGroup);
                        break;
                    }
                    else if(generation.status == "failed") {
                        await bot.deleteMessage(chatId, waitMessage.message_id);
                        await bot.sendMessage(chatId, 'Произошла ошибка при генерации!');
                        break;
                    }
                    await sleep(10000);
                }
            }
        }
        catch(error) {
            console.log(error);
        }
    });

}

function sleep(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}

module.exports = startBot;