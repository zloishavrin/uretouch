const UserModel = require('../models/user');
const bcrypt = require('bcrypt');
const uuid = require('uuid');
const UserDto  = require('../dtos/user');
const ApiError = require('../exceptions/api');
const mailService = require('../services/mail');
const tokenService = require('../services/token');

class UserService {

    async registration(email, password) {

        const candidateEmail = await UserModel.findOne({email});
        if(candidateEmail) {
            throw ApiError.BadRequestError(`Пользователь с почтовым адресом ${email} уже существует`);
        }

        const hashedPassword  = await bcrypt.hash(password, 3);
        const activationLink = uuid.v4();

        const apiKey = uuid.v4();

        const user = await UserModel.create({email, password: hashedPassword, activationLink, apiKey});
        await mailService.sendActivationMail(email, activationLink);

        const userDto = new UserDto(user);
        const tokens = tokenService.generateTokens({...userDto});
        await tokenService.saveToken(userDto.id, tokens.refreshToken);

        return {...tokens, user: userDto};

    }

    async activate(activationLink)  {

        const user  = await UserModel.findOne({activationLink});
        if(!user)  {
            throw ApiError.BadRequestError(`Некорректная ссылка активации`);
        }
        user.isActivated = true;
        await user.save();

    }

    async login(email, password) {

        const user = await UserModel.findOne({email});
        if(!user) {
            throw ApiError.BadRequestError(`Пользователь с почтой ${email} не найден`);
        }

        const isPasswordEquals = await bcrypt.compare(password, user.password);
        if(!isPasswordEquals)  {
            throw ApiError.BadRequestError(`Неверный пароль`);
        }

        const userDto = new UserDto(user);
        const tokens = tokenService.generateTokens({...userDto});

        await tokenService.saveToken(userDto.id, tokens.refreshToken);
        return {...tokens, user: userDto};

    }

    async refresh(refreshToken) {

        if(!refreshToken)  {
            throw ApiError.UnauthorizedError();
        }

        const userData = tokenService.validateRefreshToken(refreshToken);
        const tokenFromDB = await tokenService.findToken(refreshToken);
        if(!userData || !tokenFromDB) {
            throw ApiError.UnauthorizedError();
        }

        const user = await UserModel.findById(userData.id);
        const userDto = new UserDto(user);
        const tokens = tokenService.generateTokens({...userDto});

        await tokenService.saveToken(userDto.id, tokens.refreshToken);
        return {...tokens, user: userDto};

    }

    async logout(refreshToken)  {

        const token = await tokenService.removeToken(refreshToken);
        return token;
        
    }

    async getAPIKey(userId) {

        const user  = await UserModel.findById(userId);
        const apiKey = user.apiKey;
        return apiKey;

    }

    async isActivated(userId) {

        const user = await UserModel.findById(userId);
        return user.isActivated;
        
    }

}

module.exports = new UserService();