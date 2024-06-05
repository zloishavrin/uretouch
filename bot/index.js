const mongoose = require('mongoose');
const dbURI = `mongodb://${process.env.MONGO_ROOT_USER}:${process.env.MONGO_ROOT_PASSWORD}@mongo:27017/tgbot?authSource=admin`;
const startBot = require('./service/bot');

const start = async () => {
    try {
        await mongoose.connect(dbURI, { 
            useNewUrlParser: true, 
            useUnifiedTopology: true 
        });
        startBot();
        console.log('BOT IS STARTED...');
    }   
    catch(error) {
        console.log(error);
    }
}

start();