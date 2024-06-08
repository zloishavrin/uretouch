const { Schema, model } = require('mongoose');

const User = new Schema({
    chatId: { type: String, required: true, unique: true },
    step: { type: String, required: true, default: 'start',  enum: ['start', 'prompt']},
    currentPhoto: { type: String, required: false }
});

module.exports = model('User', User);