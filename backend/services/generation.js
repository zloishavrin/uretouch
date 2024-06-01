const GenerationModel = require('../models/generation');
const axios = require('axios');
const FormData = require('form-data');

class GenerationService {

    async userHistory(userId) {
        const generations = await GenerationModel.find({user: userId, status: 'completed'});
        return generations;
    }

    async getGeneration(id) {
        const generation = await GenerationModel.findById(id);
        return generation;
    }

    async createGeneration(user, prompt, file) {
        const form = new FormData();
        form.append('image', file.buffer, file.originalname);
        const newGeneration = await GenerationModel.create({user: user.id, prompt, status: 'in progress'});
        return newGeneration;
    }

    async completeGeneration(id) {
        const generation = await GenerationModel.findById(id);
        generation.status = 'completed';
        generation.url.push('https://avatars.dzeninfra.ru/get-zen_doc/271828/pub_655dd52aeece0b744ad0048e_655dfa4311aa97744330de27/scale_1200');
        generation.url.push('https://avatars.dzeninfra.ru/get-zen_doc/271828/pub_655dd52aeece0b744ad0048e_655dfa4311aa97744330de27/scale_1200');
        generation.url.push('https://avatars.dzeninfra.ru/get-zen_doc/271828/pub_655dd52aeece0b744ad0048e_655dfa4311aa97744330de27/scale_1200');
        generation.url.push('https://avatars.dzeninfra.ru/get-zen_doc/271828/pub_655dd52aeece0b744ad0048e_655dfa4311aa97744330de27/scale_1200');
        await generation.save();
        return generation;
    }

}

module.exports = new GenerationService();  