const {Schema, model} = require('mongoose');

const GenerationSchema = new Schema({
    user: {type: Schema.Types.ObjectId, ref: 'User'},
    url: {type: String, unique: true, required: true}
});

module.exports = model('Generation', GenerationSchema);