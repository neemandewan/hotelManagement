/**
 * Module dependencies.
 */

var express = require('express'),
    routes_posts = require('./routes_posts'),
    session = require('express-session'),
    favicon = require('serve-favicon'),
    morgan  = require('morgan'),
    bodyParser = require('body-parser'),
    errorHandler = require('errorHandler'),
    cookieParser = require('cookie-parser'),
    parseurl = require('parseurl'),
    app = express(),
    server;
    

    // 1. Global variables defined to be used on routes
    cfg = require('./config.js');
    fs = require('fs');
    crypto = require('crypto');
    querystring = require('querystring');
    async = require('async');
    path = require('path');
    routes = require('./routes');

if(use_cert == true) {
    http = require('https');
    hostAdd = 'https';
}else {
    http = require('http');
    hostAdd = 'http';
}

process.on('uncaughtException', function (err) {
    console.log("\nNode NOT Existing -->>> ERROR:");
    console.error(err);
});


app.set('port', process.env.PORT || server_port);               // set Primary listeniing PORT
app.set('views', __dirname + '/views');                         // set the template file location
app.set('view engine', 'ejs');                                  // Template is set to EJS
app.use(express.static(path.join(__dirname, 'www')));           // set the static files location
app.use(favicon(__dirname + '/www/favicon/favicon.ico'));       // set favicon
app.use(morgan('dev'));                                         // log every request to the console
app.use(bodyParser.urlencoded({ extended: false }));            // parse application/x-www-form-urlencoded
app.use(bodyParser.json());                                     // parse application/json

app.use(cookieParser());
// session for Express
// session for Express
app.use(session({    
    secret: 'hotelManagement webApp secret goes here',
    resave: true,
    saveUninitialized: true,
    cookie: { secure: false, maxAge: 1000*60*60} // in sec
}));

app.use(function (req, res, next) {
    var views = req.session.views;
    if (!views) {
        views = req.session.views = {};
    }

    // get the url pathname
    var pathname = parseurl(req).pathname;

    // count the views
    views[pathname] = (views[pathname] || 0) + 1;

    next();
})

// error handling middleware should be loaded after the loading the routes
if ('development' == app.get('env')) {
  app.use(errorHandler({ dumpExceptions: true, showStack: true, log: errorNotification }));
}

function errorNotification(err, str, req) {
    var title = 'Error in ' + req.method + ' ' + req.url
    notifier.notify({
        title: title,
        message: str
    })
  
}

var options = {
    pfx: fs.readFileSync(server_com),
    //passphrase: com_password,
    requestCert: request_crt,
    rejectUnauthorized: reject_unauthorized
};

if(use_cert == true){

    server = http.createServer(options, app);

    console.log('\n ++++++  Running with SSL certificate  ++++++ \n');

    server.listen(app.get('port'), function() {
        console.log("Express server listening on port " + app.get('port'));
    });

}else {

    server = http.createServer(app);

    console.log('\n ++++++  Running without SSL certificate  ++++++ \n');

    server.listen(app.get('port'), function() {
        console.log("Express server listening on port " + app.get('port'));
    });
}

app.get('/', routes.index);
app.get('/index', routes.index);
app.get('/admin', routes.main);

app.get('/api/hotels', routes_posts.getHotels);
app.post('/api/loginUser', routes_posts.loginUser);
app.post('/api/registerUser', routes_posts.registerUser);
app.post('/api/roomType', routes_posts.roomType);
app.post('/api/bookRooms', routes_posts.bookRooms);
app.post('/api/allbookings', routes_posts.allbookings);

app.post('/api/loginAdmin', routes_posts.loginAdmin);

app.post('/api/editHotel', routes_posts.editHotel);
app.post('/api/addHotel', routes_posts.addHotel);
app.post('/api/delHotel', routes_posts.delHotel);
app.post('/api/addImage', routes_posts.addImage);

app.post('/api/getRoom', routes_posts.getRoom);
app.post('/api/addRoom', routes_posts.addRoom);
app.post('/api/editRoom', routes_posts.editRoom);
app.post('/api/delRoom', routes_posts.delRoom);

app.post('/api/getAllUsers', routes_posts.getAllUsers);

app.post('/api/feature', routes_posts.feature);
app.post('/api/addFeature', routes_posts.addFeature);

app.post('/api/payment', routes_posts.payment);
app.post('/api/addPayment', routes_posts.addPayment);
