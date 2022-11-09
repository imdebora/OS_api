var port = process.env.PORT || 8080;

const express = require('express');
const path = require('path');

const app = express();

app.use(express.static(path.join(__dirname, 'build')));

app.listen(8080, (err) => {
	err ? console.log("err", err) : console.log ("Tudo funcionando!")
});

