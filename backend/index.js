const express = require('express');
const mongoose = require('mongoose');
const cors  = require('cors');
const cookieParser = require('cookie-parser');
const path = require('path');

const generationRouter = require('./routers/generation');
const authentificationRouter = require('./routers/authentification');
const userRouter = require('./routers/user');
const errorMiddleware = require('./middlewares/error');

const PORT = process.env.BACKEND_PORT || 3001;
const dbURI = `mongodb://${process.env.MONGO_ROOT_USER}:${process.env.MONGO_ROOT_PASSWORD}@mongo:27017/uretouch?authSource=admin`;

const app = express();

app.use(cors({
    credentials: true,
    origin: '*'
}));
app.use(express.json());
app.use(cookieParser());

app.use('/api/auth', authentificationRouter);
app.use('/api/generation', generationRouter);
app.use('/api/user', userRouter);
app.use('/public', express.static(path.join(__dirname, 'public')));

app.use(errorMiddleware);

app.get('/hello', (req, res) => {
    res.send("Hello World!");
});

const start = async () => {
    try {
        await mongoose.connect(dbURI, { 
            useNewUrlParser: true, 
            useUnifiedTopology: true 
        });
        app.listen(PORT, () => console.log(`Server started on port ${PORT}`));
    }   
    catch(error) {
        console.log(error);
    }
}

start();