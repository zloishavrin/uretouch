const {Schema, model} = require('mongoose');

const UserSchema = new Schema({
    email: {type: String, unique: true, required: true},
    password: {type: String, required: true},
    isActivated: {type: Boolean, default: false},
    activationLink: {type: String},
    apiKey:  {type: String, unique: true, required: true},
    registrationDate:  {type: Date, default: Date.now}
});

module.exports = model('User', UserSchema);