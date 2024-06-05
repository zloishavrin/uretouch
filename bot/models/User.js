const { Schema, model } = require('mongoose');

const User = new Schema({
    chatId: { type: String, required: true, unique: true },
    step: { type: String, required: true, default: 'start',  enum: ['start', 'prompt']},
});

module.exports = model('User', User);