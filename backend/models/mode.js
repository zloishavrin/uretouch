const {Schema, model} = require('mongoose');

const ModeSchema = new Schema({
    name: {type: String, required: true, unique: true},
    prompt:  {type: String, required: true},
    description: {type: String, required: true}
});

module.exports = model('Mode', ModeSchema);