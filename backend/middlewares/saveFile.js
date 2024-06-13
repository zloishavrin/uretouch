const multer = require('multer');
const path = require('path');
const { v4: uuidv4 } = require('uuid');

const storage = multer.diskStorage({
    destination: function (req, file, cb) {
        cb(null, 'public/');
    },
    filename: function (req, file, cb) {
        const uniqueName = uuidv4() + path.extname(file.originalname);
        cb(null, uniqueName);
    }
});

const save = multer({ storage: storage });

const saveFileNameMiddleware = (req, res, next) => {
    if (req.file) {
        req.image = req.file.filename;
    }
    next();
};

module.exports = {
    save,
    saveFileNameMiddleware
};
