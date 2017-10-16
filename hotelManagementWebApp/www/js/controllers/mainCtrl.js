function mainCtrl($scope, $anchorScroll, $rootScope, $location, $interval, $window, $timeout, $sce, $compile, usSpinnerService, authenticationService) {
    console.log("mainCtrl");

    // Initial $watch status in directive
    $scope.model = {
        navBtn: false
    };

    // Initial navigation change status
    $scope.navigationChange = 0;

    $scope.credentials = {
        email: "",
        pass: ""
    }

    $scope.noLogin = false;

    // Sub-templates for single page application
    $scope.templates = [
        { title: 'HOME',                  content: "/templates/main/welcome.html"},
        { title: 'ADD HOTEL',             content: "/templates/main/addHotel.html"},
        { title: 'EDIT HOTEL LIST',            content: "/templates/main/editHotelList.html"},
        { title: 'CUSTOMER LIST',           content: "/templates/main/userlist.html"},
        { title: 'LOGIN',                 content: "/templates/main/adminLogin.html"},
        { title: 'EDIT HOTEL',            content: "/templates/main/editHotel.html"},
        { title: 'EDIT ROOM LIST',            content: "/templates/main/editRoomList.html"},
        { title: 'ADD ROOM',            content: "/templates/main/addRoom.html"},
        { title: 'EDIT ROOM',            content: "/templates/main/editRoom.html"},
        { title: 'USER INFO',            content: "/templates/main/userinfo.html"},
        { title: 'ADD FEATURE',            content: "/templates/main/addFeature.html"}
    ];

    // Main template is initialized here
    $scope.getTemplateUrl = function() {
        if ($scope.view == undefined && window.location.search == '') {
            $scope.view = '/templates/main/adminLogin.html';
            
        }

        return $scope.view;
    }

    // Bubble div shown in dashboard
    $scope.showBubble = function() {
        $scope.viewBubble = $scope.viewBubble == true ? false: true;        
    }

    // Side Navigation is initialized here
    $scope.sideNav = function() {
        var elementExists = function() {
            var screenElement = document.getElementsByClassName("gn-open-all");            
            if(screenElement != null){
                return true;
            }
            return false;
        }
        var elementData = elementExists();
        if(elementData == true) {
            $('.gn-icon-menu').removeClass('gn-selected');
            $('.gn-menu-wrapper').removeClass('gn-open-all');
        }
    }

    $scope.genHotels = function() {
        authenticationService.getHotels().then(function(res) {
            console.log(res.data[0].images);

            $scope.hotels = res.data;
            $scope.images = [];

            for(var i =0; i<Object.keys($scope.hotels).length; i++) {
                if(Object.values($scope.hotels[i].images)[0] != undefined) {
                    $scope.images.push(Object.values($scope.hotels[i].images)[0].path);
                    $scope.hotels[i].images = Object.values($scope.hotels[i].images)[0].path;
                }else {
                    $scope.images.push("");
                    $scope.hotels[i].images = "";
                }
            }

        },function(err) {
            alert("Error");
        });

    }

    // Logic to show different sub-templates
    $scope.setTemplate = function(id) {
        $scope.sideNav();
        id = parseInt(id);
        switch(id) {
            case 0:
                $scope.genHotels();
                break;
            case 1:

                break;
            case 2:
                $scope.genHotels();
                break;
            case 3:
                $scope.getUsers();
                break;
            default:
        }

        $scope.template = $scope.templates[id];
        

        // Scroll to top
        $window.scrollTo(0, 0);
        
    }

    $scope.logAdmin = function(data) {

        authenticationService.loginAdmin(data).then(function(res) {

            if(res.status != 200 ) {
                alert("Oops! No invalid.. Try again");
            } else {
                console.log(res.data);

                $rootScope.admin = res.data;
                $scope.adminId =  $rootScope.admin.id;
                $scope.admin = res.data;

                authenticationService.getHotels().then(function(res) {
                    console.log(res.data[0].images);

                    $scope.hotels = res.data;
                    $scope.images = [];

                    for(var i =0; i<Object.keys($scope.hotels).length; i++) {
                        $scope.images.push(Object.values($scope.hotels[i].images)[0].path);
                        $scope.hotels[i].images = Object.values($scope.hotels[i].images)[0].path;
                    }

                },function(err) {
                    alert("Error");
                });

                $scope.noLogin = false;
                $scope.view = '/templates/main/dashboard.html';

                $scope.setTemplate(0);

                return $scope.view;
            }
        },function(err) {
            alert("Oops! Wrong entry.. Try again");
        });
    }

    $scope.editHotels = function (adminId, hotelObj) {
        $scope.hotelObj = hotelObj;
        $scope.hotelId = hotelObj.id;

        $scope.setTemplate(5);
    }

    $scope.editHotel = function(data) {
        var catName = "";
        switch(parseInt(data.category)) {
            case 1:
                catName = "Luxury";
                break;
            case 2:
                catName = "Resort";
                break;
            case 3:
                catName = "Aapartment";
                break;
            case 4:
                catName = "Motel";
                break;
            case 5:
                catName = "Bed and Preakfast";
                break;
        }

        var req = {
            name: data.name,
            address: data.address,
            rating: parseInt(data.rating),
            description: data.description,
            category: {
                id: parseInt(data.category),
                name: catName
            },
            status: false
        }

        req.adminId = $scope.adminId;
        req.hotelId = $scope.hotelId;
        
        authenticationService.editHotel(req).then(function(res) {
            console.log(res.data);

            alert("Completed Successfully");

            $scope.uphotel = {
                name: "",
                address: "",
                rating: "",
                description: "",
                category: {
                    id: "",
                    name: ""
                },
                status: ""
            };

        },function(err) {
            alert("Error");
        });
    }

    $scope.adHotel = function(data) {
        var catName = "";
        switch(parseInt(data.category)) {
            case 1:
                catName = "Luxury";
                break;
            case 2:
                catName = "Resort";
                break;
            case 3:
                catName = "Aapartment";
                break;
            case 4:
                catName = "Motel";
                break;
            case 5:
                catName = "Bed and Preakfast";
                break;
        }

        var req = {
            name: data.name,
            address: data.address,
            rating: parseInt(data.rating),
            description: data.description,
            category: {
                id: parseInt(data.category),
                name: catName
            },
            status: false
        }

        req.adminId = $scope.adminId;
        req.hotelId = $scope.hotelId;
        
        authenticationService.addHotel(req).then(function(res) {
            console.log(res.data);

            alert("Completed Successfully");

            $scope.addhotel = {
                name: "",
                address: "",
                rating: "",
                description: "",
                category: {
                    id: "",
                    name: ""
                },
                status: ""
            };

        },function(err) {
            alert("Error");
        });
    }

    $scope.deleteHotel = function(id, hotel) {
        var req = {};
        req.adminId = id;
        req.hotelId = hotel.id;

        authenticationService.delHotel(req).then(function(res) {
            console.log(res.data);

            alert("Completed Successfully");

            $scope.addhotel = {
                name: "",
                address: "",
                rating: "",
                description: "",
                category: {
                    id: "",
                    name: ""
                },
                status: ""
            };

            $scope.genHotels();

        },function(err) {
            alert("Error");
        });
    }

    $scope.addIm = function (im) {
        var req = {
            path: im.image
        };
        req.adminId = $scope.adminId;
        req.hotelId = $scope.hotelId;

        console.log(req);
        
        authenticationService.addImage(req).then(function(res) {
            console.log(res.data);

            alert("Completed Successfully");

            $scope.addh = "";

        },function(err) {
            alert("Error");
        });
        
    }

    $scope.getHotelRooms = function(id, hotel) {
        var req = {
            hotelId: hotel.id
        }
        authenticationService.getRoom(req).then(function(res) {
            console.log(res.data);

            $scope.rooms=res.data;
            $scope.hotelId = hotel.id;

            $scope.setTemplate(6);

        },function(err) {
            alert("Error");
        });
    }

    $scope.addRoom = function() {
        $scope.setTemplate(7);
    }

    $scope.addRoomInHotel = function(data) {
        console.log(data);
        var desc = "";
        var occupancy = "";
        switch(parseInt(data.roomtype)) {
            case 1:
                desc = "Single";
                occupancy = 1;
                break;
            case 2:
                desc = "Double";
                occupancy = 2;
                break;
            case 3:
                desc = "Studio";
                occupancy = 2;
                break;
            case 4:
                desc = "Presedental Suite";
                occupancy = 6;
                break;
        }

        var req = {
            floor: data.floor,
            room_number: data.roomNumber,
            typeDescription: desc,
            typeOccupacy: occupancy,
            typeId: data.roomtype,
            price: data.rate
        }

        

        req.hotelId = $scope.hotelId;

        console.log(req);

        authenticationService.addRoom(req).then(function(res) {
            alert("Done");
            //$scope.setTemplate(0);

        },function(err) {
            alert("Error");
        });
    }

    $scope.editRoom = function(adminId, room) {
        $scope.roomId = room.id;
        $scope.setTemplate(8);
    }

    $scope.editRoomInHotel = function(data) {
        console.log(data);
        var desc = "";
        var occupancy = "";
        switch(parseInt(data.roomtype)) {
            case 1:
                desc = "Single";
                occupancy = 1;
                break;
            case 2:
                desc = "Double";
                occupancy = 2;
                break;
            case 3:
                desc = "Studio";
                occupancy = 2;
                break;
            case 4:
                desc = "Presedental Suite";
                occupancy = 6;
                break;
        }

        var req = {
            floor: data.floor,
            room_number: data.roomNumber,
            typeDescription: desc,
            typeOccupacy: occupancy,
            typeId: data.roomtype,
            price: data.rate
        }

        
        req.hotelId = $scope.hotelId;
        req.roomId = $scope.roomId;

        console.log(req);

        authenticationService.editRoom(req).then(function(res) {

            $scope.setTemplate(0);

        },function(err) {
            alert("Error");
        });
    }

    $scope.deleteRoom = function(adminId, room) {

        var req = {};
        req.hotelId = $scope.hotelId;
        req.roomId = room.id;

        console.log(req);

        authenticationService.delRoom(req).then(function(res) {

            $scope.setTemplate(0);

        },function(err) {
            alert("Error");
        });
    }

    $scope.getUsers = function() {

        authenticationService.getAllUsers().then(function(res) {

            $scope.users = res.data;

        },function(err) {
            alert("Error");
        });
    }

    $scope.feature = function(data) {
        $scope.userId = data;

        var req = {};
        req.userId = data;

        authenticationService.feature(req).then(function(res) {

            $scope.features = res.data;
            $scope.setTemplate(9);


        },function(err) {
            alert("Error");
        });
    }

    $scope.addfeature = function (argument) {
        $scope.setTemplate(10);
    }

    $scope.featureAdd = function(data) {

        var req = {};
        req.userId = $scope.userId;
        req.featureName = data.featureType;
        req.featurePrice = data.rate;

        console.log(req);

        authenticationService.addFeature(req).then(function(res) {

            $scope.features = res.data;

        },function(err) {
            alert("Error");
        });
    }

    $scope.logout = function() {
        $rootScope.admin = null;
        $scope.admin = null;
        $scope.adminId = null;
        $window.location.reload();
    }
}