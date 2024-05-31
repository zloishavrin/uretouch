module.exports = class UserDto {
    email;
    login;
    id;
    isActivated;

    constructor(model) {
        this.email = model.email;
        this.login  = model.login;
        this.id  = model.id;
        this.isActivated  = model.isActivated;
    }
}