const GenerationModel = require('../models/generation');
const ModeModel = require('../models/mode');
const axios = require('axios');
const FormData = require('form-data');
const path = require('path');
const fs = require('fs');

class GenerationService {

    async userHistory(userId) {
        const generations = await GenerationModel.find({user: userId}).sort({ date: -1 });;
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

    async createGeneration(user, mode, file) {
        const form = new FormData();
        const filePath = path.resolve('public', file.filename);
        form.append('image', fs.createReadStream(filePath));
        const prompt = mode.description || mode;
        const newGeneration = await GenerationModel.create({user: user.id, prompt, status: 'inProgress', original: `${process.env.CLIENT_URL}public/${file.filename}`});
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

    async getMods() {
        const mods = await ModeModel.find();
        if (Array.isArray(mods)) {
            const updatedMode = mods.map((item) => {
                const { prompt, ...rest } = item.toObject();
                return rest;
            });
            return updatedMode;
        }
        return mods;
    }

    async getMode(id) {
        const mode = await ModeModel.findById(id);    
        return mode;
    }

}

module.exports = new GenerationService();  