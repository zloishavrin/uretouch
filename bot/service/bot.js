const TelegramBot = require('node-telegram-bot-api');
const userService = require('./user');

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
    })
}

module.exports = startBot;