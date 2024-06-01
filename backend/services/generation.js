const GenerationModel = require('../models/generation');

class GenerationService {

    async userHistory(userId) {

        const generations = await GenerationModel.find({user: userId});
        return generations;

    }

    async getGeneration(id) {

        const generation = await GenerationModel.findById(id);
        return generation;

    }

}

module.exports = new GenerationService();