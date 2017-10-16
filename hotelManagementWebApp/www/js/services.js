app.service("authenticationService",['$q', '$http', '$cookieStore', '$rootScope', '$timeout', '$base64' ,
    function($q, $http, $cookieStore, $rootScope, $timeout, $base64) {

        var service = {};
        service.getHotels = getHotels;
        service.loginUser = loginUser;
        service.registerUser = registerUser;
        service.getRoomType = getRoomType;
        service.bookRooms = bookRooms;
        service.allBookings = allBookings;
        service.addPayment = addPayment;
        service.payment = payment;


        service.loginAdmin = loginAdmin;

        service.editHotel = editHotel;
        service.addHotel = addHotel;
        service.delHotel = delHotel;
        service.addImage = addImage;

        service.getRoom = getRoom;
        service.addRoom = addRoom;
        service.editRoom = editRoom;
        service.delRoom = delRoom;

        service.getAllUsers = getAllUsers;

        service.feature = feature;
        service.addFeature = addFeature;

        return service;

        var deff;

        function payment(data) {

            deff = $q.defer();

            $http.post('/api/payment', data)
               .then(function (response) {

                   deff.resolve(response);

                }, function(err) {
                    
                    console.log(err);
                    deff.reject(err);
                }
            );

            return deff.promise;
        }

        function feature(data) {

            deff = $q.defer();

            $http.post('/api/feature', data)
               .then(function (response) {

                   deff.resolve(response);

                }, function(err) {
                    
                    console.log(err);
                    deff.reject(err);
                }
            );

            return deff.promise;
        }

        function addFeature(data) {

            deff = $q.defer();

            $http.post('/api/addFeature', data)
               .then(function (response) {

                   deff.resolve(response);

                }, function(err) {
                    
                    console.log(err);
                    deff.reject(err);
                }
            );

            return deff.promise;
        }
        

        function getAllUsers() {

            deff = $q.defer();

            $http.post('/api/getAllUsers', {})
               .then(function (response) {

                   deff.resolve(response);

                }, function(err) {
                    
                    console.log(err);
                    deff.reject(err);
                }
            );

            return deff.promise;
        }

        function editHotel(data) {

            deff = $q.defer();

            $http.post('/api/editHotel', data)
               .then(function (response) {

                   deff.resolve(response);

                }, function(err) {
                    
                    console.log(err);
                    deff.reject(err);
                }
            );

            return deff.promise;
        }

        function addHotel(data) {

            deff = $q.defer();

            $http.post('/api/addHotel', data)
               .then(function (response) {

                   deff.resolve(response);

                }, function(err) {
                    
                    console.log(err);
                    deff.reject(err);
                }
            );

            return deff.promise;
        }

        function delHotel(data) {

            deff = $q.defer();

            $http.post('/api/delHotel', data)
               .then(function (response) {

                   deff.resolve(response);

                }, function(err) {
                    
                    console.log(err);
                    deff.reject(err);
                }
            );

            return deff.promise;
        }

        function addImage(data) {

            deff = $q.defer();

            $http.post('/api/addImage', data)
               .then(function (response) {

                   deff.resolve(response);

                }, function(err) {
                    
                    console.log(err);
                    deff.reject(err);
                }
            );

            return deff.promise;
        }

        function getRoom(data) {

            deff = $q.defer();

            $http.post('/api/getRoom', data)
               .then(function (response) {

                   deff.resolve(response);

                }, function(err) {
                    
                    console.log(err);
                    deff.reject(err);
                }
            );

            return deff.promise;
        }

        function addRoom(data) {

            deff = $q.defer();

            $http.post('/api/addRoom', data)
               .then(function (response) {

                   deff.resolve(response);

                }, function(err) {
                    
                    console.log(err);
                    deff.reject(err);
                }
            );

            return deff.promise;
        }

        function editRoom(data) {

            deff = $q.defer();

            $http.post('/api/editRoom', data)
               .then(function (response) {

                   deff.resolve(response);

                }, function(err) {
                    
                    console.log(err);
                    deff.reject(err);
                }
            );

            return deff.promise;
        }

        function delRoom(data) {

            deff = $q.defer();

            $http.post('/api/delRoom', data)
               .then(function (response) {

                   deff.resolve(response);

                }, function(err) {
                    
                    console.log(err);
                    deff.reject(err);
                }
            );

            return deff.promise;
        }

        function getHotels() {

            deff = $q.defer();

            $http.get('/api/hotels')
               .then(function (response) {

                   deff.resolve(response);

                }, function(err) {
                    
                    console.log(err);
                    deff.reject(err);
                }
            );

            return deff.promise;
        }

        function loginUser(data) {

            deff = $q.defer();

            $http.post('/api/loginUser', data)
               .then(function (response) {

                   deff.resolve(response);

                }, function(err) {
                    
                    console.log(err);
                    deff.reject(err);
                }
            );

            return deff.promise;
        }

        function registerUser(data) {

            deff = $q.defer();

            $http.post('/api/registerUser', data)
               .then(function (response) {

                   deff.resolve(response);

                }, function(err) {
                    
                    console.log(err);
                    deff.reject(err);
                }
            );

            return deff.promise;
        }

        function getRoomType(data) {
            console.log("data -->> " + data);

            deff = $q.defer();

            $http.post('/api/roomType', {value: data})
               .then(function (response) {

                   deff.resolve(response);

                }, function(err) {
                    
                    console.log(err);
                    deff.reject(err);
                }
            );

            return deff.promise;
        }

        function bookRooms(data, userId, hotelId) {

            data.userId  = userId;
            data.hotelId = hotelId;

            deff = $q.defer();

            $http.post('/api/bookRooms', data)
               .then(function (response) {

                   deff.resolve(response);

                }, function(err) {
                    
                    console.log(err);
                    deff.reject(err);
                }
            );

            return deff.promise;
        }

        function allBookings(id) {

            deff = $q.defer();

            $http.post('/api/allbookings', {userId: id})
               .then(function (response) {

                   deff.resolve(response);

                }, function(err) {
                    
                    console.log(err);
                    deff.reject(err);
                }
            );

            return deff.promise;
        }

        function addPayment(data) {

            deff = $q.defer();

            $http.post('/api/addPayment', data)
               .then(function (response) {

                   deff.resolve(response);

                }, function(err) {
                    
                    console.log(err);
                    deff.reject(err);
                }
            );

            return deff.promise;
        }
        
        function loginAdmin(data) {

            deff = $q.defer();

            $http.post('/api/loginAdmin', data)
               .then(function (response) {

                   deff.resolve(response);

                }, function(err) {
                    deff.reject(err);
                }
            );

            return deff.promise;
        }


        
    }    
]);