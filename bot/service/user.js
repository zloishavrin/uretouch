const User = require('../models/User');

class UserSerivce {

    async createUser(chatId) {
        const user = await User.create({ chatId });
        return user;
    }

    async getUser(chatId)  {
        const user  = await User.findOne({ chatId  });
        return user;
    }

    async changeStep(chatId, step)  {
        const user = await User.findOneAndUpdate({ chatId  }, { step: step });
        return user;
    }

    async getPhoto(chatId)   {
        const user = await User.findOne({ chatId });
        return user.currentPhoto;
    }

    async setPhoto(chatId, photoId) {
        const user = await User.findOneAndUpdate({ chatId }, { currentPhoto: photoId });
        return user;
    }

}

module.exports = new UserSerivce();