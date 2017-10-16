var httpHelper = require('../util/httpHelper');
var aesjs = require('aes-js');
var callback;

exports.index = function(req, res) {
    console.log(" ==== Index ==== ");
    res.render('index', { title: 'Express' });
};

exports.main = function(req, res) {
    console.log(" ==== main ==== ");

    res.render('main', { title: 'Express' });
};