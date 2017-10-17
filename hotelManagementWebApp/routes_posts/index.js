var httpHelper = require('../util/httpHelper');
var aesjs = require('aes-js');

exports.getHotels = function(req, res) {
    var data = req.body;

    if(!data) return false;

    httpHelper.makeRequest(hotels, 'GET', 'application/json',
        JSON.stringify({           
         
        }), function (responseGetString) {

            var responseJSON = JSON.parse(responseGetString);

            console.log("responseJSON ----->>>>> ");
            console.log(responseJSON);

            if(responseJSON.status == 200 || responseJSON.status == "200") {

                res.status(200).send(responseJSON.data);

            }else {
                res.status(404).send("ERROR");
            }
      });
};

exports.loginUser = function(req, res) {
    var data = req.body;
    console.log(data.pass);

    if(!data) return false;

    httpHelper.makeRequest(login, 'POST', 'application/json',
        JSON.stringify({           
            email: data.email,
            password: data.pass
        }), function (responseGetString) {

            var responseJSON = JSON.parse(responseGetString);

            console.log("responseJSON ----->>>>> ");
            console.log(responseJSON);

            if(responseJSON.status == 200 || responseJSON.status == "200") {

                res.status(200).send(responseJSON.data);

            }else {
                res.status(404).send("ERROR");
            }
      });
};

exports.registerUser = function(req, res) {
    var data = req.body;
    console.log(data);

    if(!data) return false;

    httpHelper.makeRequest(register, 'POST', 'application/json',
        JSON.stringify({           
                "email": data.email,
                "password": data.password,
                "name": data.name,
                "lastName": data.lastName
        }), function (responseGetString) {

            var responseJSON = JSON.parse(responseGetString);

            console.log("responseJSON ----->>>>> ");
            console.log(responseJSON);

            if(responseJSON.status == 200 || responseJSON.status == "200") {

                res.status(200).send(responseJSON.data);

            }else {
                res.status(404).send("ERROR");
            }
      });
};

exports.roomType = function(req, res) {
    var data = req.body;
    console.log(data);

    if(!data) return false;

    httpHelper.makeRequest('/api/bookings/'+data.value+'/getRoomTypes', 'GET', 'application/json',
        JSON.stringify({           
        }), function (responseGetString) {

            var responseJSON = JSON.parse(responseGetString);

            console.log("responseJSON ----->>>>> ");
            console.log(responseJSON);

            if(responseJSON.status == 200 || responseJSON.status == "200") {

                res.status(200).send(responseJSON.data);

            }else {
                res.status(404).send("ERROR");
            }
      });
};

exports.bookRooms = function(req, res) {
    var data = req.body;
    console.log(data);

    if(!data) return false;

    httpHelper.makeRequest('/api/bookings/'+data.hotelId+'/' + data.userId, 'POST', 'application/json',
        JSON.stringify({  
            "beginDate": data.beginDate,
            "endDate": data.closeDate,
            "noOfRooms": data.noOfRooms,
            "roomType": data.categoryId        
        }), function (responseGetString) {

            var responseJSON = JSON.parse(responseGetString);

            console.log("responseJSON ----->>>>> ");
            console.log(responseJSON);

            if(responseJSON.status == 200 || responseJSON.status == "200") {

                res.status(200).send(responseJSON.data);

            }else {
                res.status(404).send("ERROR");
            }
      });
};

exports.allbookings = function(req, res) {
    var data = req.body;
    console.log(data);

    if(!data) return false;

    httpHelper.makeRequest('/api/bookings/'+data.userId, 'GET', 'application/json',
        JSON.stringify({ 
        }), function (responseGetString) {

            var responseJSON = JSON.parse(responseGetString);

            console.log("responseJSON ----->>>>> ");
            console.log(responseJSON);

            if(responseJSON.status == 200 || responseJSON.status == "200") {

                res.status(200).send(responseJSON.data);

            }else {
                res.status(404).send("ERROR");
            }
      });
};

exports.loginAdmin = function(req, res) {
    var data = req.body;
    console.log(data.pass);

    if(!data) return false;

    httpHelper.makeRequest(login, 'POST', 'application/json',
        JSON.stringify({           
            email: data.email,
            password: data.pass
        }), function (responseGetString) {

            var responseJSON = JSON.parse(responseGetString);

            console.log("responseJSON ----->>>>> ");
            console.log(responseJSON);

            if(responseJSON.status == 200 || responseJSON.status == "200") {

                if(responseJSON.data.role[0].role == "ADMIN") {
                    res.status(200).send(responseJSON.data);
                }else {
                    res.status(404).send("ERROR");
                }                

            }else {
                res.status(404).send("ERROR");
            }
      });
};


exports.editHotel = function(req, res) {
    var data = req.body;

    if(!data) return false;

    httpHelper.makeRequest('/api/hotels/'+data.adminId+'/'+data.hotelId, 'PUT', 'application/json',
        JSON.stringify({           
            "name": data.name,
            "address": data.address,
            "rating": data.rating,
            "description": data.description,
            "category": {
                "id": data.category.id,
                "name": data.category.name 
            },
            "status": data.status
        }), function (responseGetString) {

            var responseJSON = JSON.parse(responseGetString);

            console.log("responseJSON ----->>>>> ");
            console.log(responseJSON);

            if(responseJSON.status == 200 || responseJSON.status == "200") {

                res.status(200).send(responseJSON.data);               

            }else {
                res.status(404).send("ERROR");
            }
      });
};


exports.addHotel = function(req, res) {
    var data = req.body;

    if(!data) return false;

    httpHelper.makeRequest('/api/hotels/'+data.adminId, 'POST', 'application/json',
        JSON.stringify({           
            "name": data.name,
            "address": data.address,
            "rating": data.rating,
            "description": data.description,
            "category": {
                "id": data.category.id,
                "name": data.category.name 
            },
            "status": data.status
        }), function (responseGetString) {

            var responseJSON = JSON.parse(responseGetString);

            console.log("responseJSON ----->>>>> ");
            console.log(responseJSON);

            if(responseJSON.status == 200 || responseJSON.status == "200") {

                res.status(200).send(responseJSON.data);              

            }else {
                res.status(404).send("ERROR");
            }
      });
};

exports.delHotel = function(req, res) {
    var data = req.body;
    console.log(data);

    if(!data) return false;

    httpHelper.makeRequest('/api/hotels/'+data.adminId+'/'+ data.hotelId, 'DELETE', 'application/json',
        JSON.stringify({           
            
        }), function (responseGetString) {

            var responseJSON = JSON.parse(responseGetString);

            console.log("responseJSON ----->>>>> ");
            console.log(responseJSON);

            if(responseJSON.status == 200 || responseJSON.status == "200") {

                res.status(200).send(responseJSON.data);             

            }else {
                res.status(404).send("ERROR");
            }
      });
};

exports.addImage = function(req, res) {
    var data = req.body;
    console.log(data);

    if(!data) return false;

    httpHelper.makeRequest('/api/hotels/'+data.hotelId+'/image/', 'POST', 'application/json',
        JSON.stringify({           
            "path": data.path
        }), function (responseGetString) {

            var responseJSON = JSON.parse(responseGetString);

            console.log("responseJSON ----->>>>> ");
            console.log(responseJSON);

            if(responseJSON.status == 200 || responseJSON.status == "200") {

                res.status(200).send(responseJSON.data);              

            }else {
                res.status(404).send("ERROR");
            }
      });
};

exports.getRoom = function(req, res) {
    var data = req.body;
    console.log(data);

    if(!data) return false;

    httpHelper.makeRequest('/api/rooms/'+data.hotelId, 'GET', 'application/json',
        JSON.stringify({

        }), function (responseGetString) {

            var responseJSON = JSON.parse(responseGetString);

            console.log("responseJSON ----->>>>> ");
            console.log(responseJSON);

            if(responseJSON.status == 200 || responseJSON.status == "200") {

                res.status(200).send(responseJSON.data);

            }else {
                res.status(404).send("ERROR");
            }
      });
};

exports.addRoom = function(req, res) {
    var data = req.body;
    console.log(data);

    if(!data) return false;

    httpHelper.makeRequest('/api/rooms/'+data.hotelId, 'POST', 'application/json',
        JSON.stringify({           
            "floor": data.floor,
            "room_number": data.room_number,
            "typeDescription": data.typeDescription,
            "typeOccupacy": data.typeOccupacy,
            "typeId": data.typeId,
            "price": data.price
        }), function (responseGetString) {

            var responseJSON = JSON.parse(responseGetString);

            console.log("responseJSON ----->>>>> ");
            console.log(responseJSON);

            if(responseJSON.status == 200 || responseJSON.status == "200") {

                res.status(200).send(responseJSON.data);              

            }else {
                res.status(404).send("ERROR");
            }
      });
};

exports.editRoom = function(req, res) {
    var data = req.body;
    console.log(data);

    if(!data) return false;

    httpHelper.makeRequest('/api/rooms/'+data.hotelId+'/'+data.roomId, 'PUT', 'application/json',
        JSON.stringify({           
            "floor": data.floor,
            "room_number": data.room_number,
            "typeDescription": data.typeDescription,
            "typeOccupacy": data.typeOccupacy,
            "typeId": data.typeId,
            "price": data.price
        }), function (responseGetString) {

            var responseJSON = JSON.parse(responseGetString);

            console.log("responseJSON ----->>>>> ");
            console.log(responseJSON);

            if(responseJSON.status == 200 || responseJSON.status == "200") {

                res.status(200).send(responseJSON.data);              

            }else {
                res.status(404).send("ERROR");
            }
      });
};

exports.delRoom = function(req, res) {
    var data = req.body;
    console.log(data);

    if(!data) return false;

    httpHelper.makeRequest('/api/rooms/'+data.hotelId+'/'+ data.roomId, 'DELETE', 'application/json',
        JSON.stringify({
            
        }), function (responseGetString) {

            var responseJSON = JSON.parse(responseGetString);

            console.log("responseJSON ----->>>>> ");
            console.log(responseJSON);

            if(responseJSON.status == 200 || responseJSON.status == "200") {

                res.status(200).send(responseJSON.data);               

            }else {
                res.status(404).send("ERROR");
            }
      });
};

exports.getAllUsers = function(req, res) {
    var data = req.body;
    console.log(data);

    httpHelper.makeRequest(users,
         'GET', 'application/json',
        JSON.stringify({
            
        }), function (responseGetString) {

            var responseJSON = JSON.parse(responseGetString);

            console.log("responseJSON ----->>>>> ");
            console.log(responseJSON);

            if(responseJSON.status == 200 || responseJSON.status == "200") {

                res.status(200).send(responseJSON.data);               

            }else {
                res.status(404).send("ERROR");
            }
      });
};

exports.feature = function(req, res) {
    var data = req.body;
    console.log(data);

    if(!data) return false;

    httpHelper.makeRequest('/api/feature/'+ data.userId, 'GET', 'application/json',
        JSON.stringify({           
        }), function (responseGetString) {

            var responseJSON = JSON.parse(responseGetString);

            console.log("responseJSON ----->>>>> ");
            console.log(responseJSON);

            if(responseJSON.status == 200 || responseJSON.status == "200") {

                res.status(200).send(responseJSON.data);              

            }else {
                res.status(404).send("ERROR");
            }
      });
};


exports.addFeature = function(req, res) {
    var data = req.body;
    console.log(data);

    if(!data) return false;

    httpHelper.makeRequest('/api/feature/'+ data.userId, 'POST', 'application/json',
        JSON.stringify({           
            "userId": data.userId,
            "featureName": data.featureName,
            "featurePrice": data.featurePrice
        }), function (responseGetString) {

            var responseJSON = JSON.parse(responseGetString);

            console.log("responseJSON ----->>>>> ");
            console.log(responseJSON);

            if(responseJSON.status == 200 || responseJSON.status == "200") {

                res.status(200).send(responseJSON.data);              

            }else {
                res.status(404).send("ERROR");
            }
      });
};

exports.payment = function(req, res) {
    var data = req.body;
    console.log(data);

    if(!data) return false;

    httpHelper.makeRequest('/api/payment/'+ data.customerId, 'GET', 'application/json',
        JSON.stringify({           
        }), function (responseGetString) {

            var responseJSON = JSON.parse(responseGetString);

            console.log("responseJSON ----->>>>> ");
            console.log(responseJSON);

            if(responseJSON.status == 200 || responseJSON.status == "200") {

                res.status(200).send(responseJSON.data);              

            }else {
                res.status(404).send("ERROR");
            }
      });
};


exports.addPayment = function(req, res) {
    var data = req.body;
    console.log(data);

    if(!data) return false;

    httpHelper.makeRequest('/api/payment/'+ data.customerId, 'POST', 'application/json',
        JSON.stringify({           
            customerId: data.customerId,
            amount: data.amount,
            status: data.status,
            paymentType: data.paymentType,
            bookingId: data.bookingId
        }), function (responseGetString) {

            var responseJSON = JSON.parse(responseGetString);

            console.log("responseJSON ----->>>>> ");
            console.log(responseJSON);

            if(responseJSON.status == 200 || responseJSON.status == "200") {

                res.status(200).send(responseJSON.data);              

            }else {
                res.status(404).send("ERROR");
            }
      });
};
