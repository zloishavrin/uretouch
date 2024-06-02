const GenerationModel = require('../models/generation');
const axios = require('axios');
const FormData = require('form-data');

class GenerationService {

    async userHistory(userId) {
        const generations = await GenerationModel.find({user: userId});
        return generations;
    }

    async userJobs(userId)  {
        const generations = await GenerationModel.find({user: userId, status: 'inProgress'});
        return generations;
    }

    async getGeneration(id) {
        const generation = await GenerationModel.findById(id);
        return generation;
    }

    async getGenerations(idList) {
        const generations = await GenerationModel.find({_id: {$in: idList}});
        return generations;
    }

    async createGeneration(user, prompt, file) {
        const form = new FormData();
        form.append('image', file.buffer, file.originalname);
        const newGeneration = await GenerationModel.create({user: user.id, prompt, status: 'inProgress'});
        return newGeneration;
    }

    async completeGeneration(id) {
        const generation = await GenerationModel.findById(id);
        generation.status = 'completed';
        generation.original = 'https://24tort.ru/img/Origin%D0%B1%D0%B0%D0%B1%D1%83%D0%BB%D0%B5_9.png'
        generation.url.push('https://avatars.dzeninfra.ru/get-zen_doc/271828/pub_655dd52aeece0b744ad0048e_655dfa4311aa97744330de27/scale_1200');
        generation.url.push('https://avatars.dzeninfra.ru/get-zen_doc/271828/pub_655dd52aeece0b744ad0048e_655dfa4311aa97744330de27/scale_1200');
        generation.url.push('https://avatars.dzeninfra.ru/get-zen_doc/271828/pub_655dd52aeece0b744ad0048e_655dfa4311aa97744330de27/scale_1200');
        generation.url.push('https://avatars.dzeninfra.ru/get-zen_doc/271828/pub_655dd52aeece0b744ad0048e_655dfa4311aa97744330de27/scale_1200');
        await generation.save();
        return generation;
    }

}

module.exports = new GenerationService();  