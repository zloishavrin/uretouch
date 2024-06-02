const {Schema, model} = require('mongoose');

const GenerationSchema = new Schema({
    user: {type: Schema.Types.ObjectId, ref: 'User'},
    prompt: {type: String, required: true},
    status: {type: String, required: true, enum: ['inProgress', 'completed', 'failed']},
    url: {type: [String], default: []}
});

module.exports = model('Generation', GenerationSchema);