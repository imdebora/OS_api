var port = process.env.PORT || 3306;

const express = require('express');
const path = require('path');

const app = express();

app.use(express.static(path.join(__dirname, 'build')));

app.listen(3306, (err) => {
	err ? console.log("err", err) : console.log ("Tudo funcionando!")
});

