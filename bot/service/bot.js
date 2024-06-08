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
            buttons.push([{ text: 'Ручной режим', callback_data: `mode_custom` }]);

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

            if(callbackData == 'mode_custom') {
                const photoId  = msg.message.photo[msg.message.photo.length-1].file_id;
                await userService.setPhoto(chatId, photoId);
                await userService.changeStep(chatId, 'prompt');
                await bot.sendMessage(chatId, "Введите промпт");
            }
            else if(callbackData.startsWith('mode_')) {
                const modeId  = callbackData.replace('mode_', '');
                const photoId  = msg.message.photo[msg.message.photo.length-1].file_id;
                const photoUrl = await bot.getFileLink(photoId);
                const generationData = await generateService.startGeneration(null, modeId, photoUrl);
                console.log(generationData);
                //await bot.sendMessage(chatId, generationData);
            }
        }
        catch(error) {
            console.log(error);
        }
    });

}

module.exports = startBot;