function indexCtrl($scope, $rootScope, $timeout, $location, $window, $sce, $compile, $window, authenticationService) {
    console.log("loginCtrl");

    if($rootScope.customer == null) {
        $scope.noLogin = true;
    }else {
        $scope.noLogin = false;
    }

    $scope.booking =  {
        beginDate: "",
        categoryId: "",
        closeDate: "",
        noOfRooms: ""
    }

    $scope.credentials = {
        email: "",
        pass: ""
    }

    $scope.cred = {
        name: "",
        lastName: "",
        email: "",
        password: "",
        cpass: ""
    }

    authenticationService.getHotels().then(function(res) {
        //console.log(res.data[0].images);

       	$scope.hotels = res.data;
       	$scope.images = [];

       	for(var i =0; i<Object.keys($scope.hotels).length; i++) {
       		$scope.images.push(Object.values($scope.hotels[i].images)[0].path);
       		$scope.hotels[i].images = Object.values($scope.hotels[i].images)[0].path;
       	}

    },function(err) {
        alert(err);
    });

    $scope.book = function (customerId, hotelObj) {
    	console.log(customerId);
    	if(customerId == null) {
    		$scope.showEventsDialog = true;
    	}else {
            $scope.hotelObj = hotelObj;
            $scope.hotelId = hotelObj.id;
            authenticationService.getRoomType(hotelObj.id).then(function(res) {
                if(res.status != 200) {
                    alert("Oops! something went wrong...");
                }
                
                $scope.categories = res.data;

                $scope.view = '/templates/login/bookhotel.html';
                return $scope.view;

                
                
            },function(err) {
                alert(err);
            });
        }
    }

    $scope.hideEventsDialog = function () {
    	$scope.showEventsDialog = false;
    }

    // Main template is initialized here
    $scope.getTemplateUrl = function() {
        if ($scope.view == undefined && window.location.search == '') {
            $scope.view = '/templates/login/user.html';
        }

        return $scope.view;
    }

    $scope.login = function() {
    	$scope.view = '/templates/login/login.html';

    	return $scope.view;
    }

    $scope.register = function() {
        $scope.err = '';
        $scope.view = '/templates/login/register.html';

        return $scope.view;
    }

    $scope.logUser = function(data) {
    	authenticationService.loginUser(data).then(function(res) {
            if(res.status != 200) {
                alert("Oops! No user.. Please register");
            }
	        console.log(res.data);

            $rootScope.customer = res.data;
            $scope.customerId =  $rootScope.customer.id;
            $scope.user = res.data;
            $scope.noLogin = false;
            $scope.view = '/templates/login/user.html';

            return $scope.view;
	       	
	    },function(err) {
	        alert("Invalid Login.. Please register to login.");
	    });
    }

    $scope.ch = function() {
        $scope.err = "";
    }

    $scope.checkPass = function(data1, data2) {
        if(data1 == "" || data1 == null) {
            $scope.err = "";
        }else if(data2 != data1) {
            $scope.err = "Password does not match";
        }else {
            $scope.err = "";
        }
    }

    $scope.registerForm = function(data) {
        console.log(data);

        authenticationService.registerUser(data).then(function(res) {
            if(res.status != 200) {
                alert("Oops! No user.. Please register");
            }
            console.log(res.data);

            $scope.view = '/templates/login/login.html';

            return $scope.view;
            
        },function(err) {
            alert(err);
        });
    }

    $scope.goToRegister = function() {
        $scope.view = '/templates/login/register.html';

        return $scope.view;
    }

    $scope.home = function() {
       $window.location.reload();
    }

    $scope.logout = function() {
        $rootScope.customer = null;
        $window.location.reload();
    }

    $scope.bookRoomBack = function() {
        $scope.view = '/templates/login/user.html';
        return $scope.view;
    }

    $scope.bookRoom = function(data, customerId, hotedId) {
        if(data.beginDate == null || data.beginDate == "" || 
            data.categoryId == null || data.categoryId == "" ||
            data.closeDate == null || data.closeDate == "" ||
            data.noOfRooms == null || data.noOfRooms == "") {
            alert("Enter fields correctly") 
        }

        var bd = data.beginDate.split("-")[1];
        var ed = data.closeDate.split("-")[1];

        if(bd > ed) return alert("invalid dates");

        $scope.booking =  {
            beginDate: "",
            categoryId: "",
            closeDate: "",
            noOfRooms: ""
        }

        console.log(data);

        authenticationService.bookRooms(data, customerId, hotedId).then(function(res) {
            if(res.status != 200) {
                alert("Oops! No user.. Please register");
            }
            console.log(res.data);

            $scope.myBookings();
            
        },function(err) {
            alert(err);
        });
    }

    $scope.myBookings = function() {

        authenticationService.allBookings($scope.customerId).then(function(res) {
            if(res.status != 200) {
                alert("Oops! No user.. Please register");
            }
            console.log(res.data);

            $scope.bookings1 = res.data;

            var req = {};
            req.customerId = $scope.customerId;

            authenticationService.payment(req).then(function(res) {
                console.log("payment");
                
                var payment = res.data;

                console.log("bookings")
                
                var bookings = $scope.bookings1;

                var result = [];

                for(var i=0; i<Object.keys(bookings).length; i++) {
                    var booking = bookings[i];
                    var present = false;
                    for(var j=0; j<Object.keys(payment).length; j++) {    
                        if(booking.id == payment[j].bookingId) {
                            present = true;
                        }
                    }

                    booking.status = present;
                    result.push(booking);
                    
                }

                $scope.bookings = result;
                
            },function(err) {
                alert(err);
            });

            $scope.view = '/templates/login/mybookings.html';

            return $scope.view;
            
        },function(err) {
            alert(err);
        });
    }

    $scope.homeses = function() {
        $scope.view = '/templates/login/user.html';

        return $scope.view;
    }

    $scope.payBooking = function(booking) {
        var days = booking.end_date.dayOfMonth - booking.begin_date.dayOfMonth;
        var amount = booking.rooms[0].price*Object.keys(booking.rooms).length;

        $scope.payAmt = amount*(parseInt(days)+1);
        $scope.payId = booking.id;

        $scope.view = '/templates/login/pay.html';

        return $scope.view;
    }

    $scope.pay = function(payAmt, payType) {
        if(payType ==  undefined || payType == "") {
            alert("Please select payment type")
            return;
        }

        var req = {
            customerId: $scope.customerId,
            amount: payAmt,
            status: true,
            paymentType: payType,
            bookingId: $scope.payId
        }
        

        authenticationService.addPayment(req).then(function(res) {
            if(res.status != 200) {
                alert("Oops! Something went wrong!!!");
            }
            console.log(res.data);

            alert("Amount paid successfully");

            $scope.myBookings();
            
        },function(err) {
            alert(err);
        });
    }

    $scope.myProfile = function() {
        $scope.view = '/templates/login/myprofile.html';

        return $scope.view;
    }

}