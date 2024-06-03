const {Schema, model} = require('mongoose');

const GenerationSchema = new Schema({
    user: {type: Schema.Types.ObjectId, ref: 'User'},
    prompt: {type: String, required: true},
    status: {type: String, required: true, enum: ['inProgress', 'completed', 'failed']},
    url: {type: [String], default: []},
    original: {type: String},
    date:  {type: Date, default: Date.now}
});

module.exports = model('Generation', GenerationSchema);