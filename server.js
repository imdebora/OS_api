const express = require("express");
const path = require('path');

const app = express();

app.use(express.static(path.join(__dirname, 'build')));

app.listen(process.env.PORT || 8080, (err) => {
	err ? console.log("err", err) : console.log ("Tudo funcionando!")
});

