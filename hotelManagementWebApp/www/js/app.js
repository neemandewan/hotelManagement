var app = angular.module('app', [
    'ngRoute', 
    'ngSanitize', 
    'ngCookies', 
    'base64', 
    '720kb.datepicker',
    'angularSpinner'
]);

app.config(function($routeProvider, $locationProvider) {
    $routeProvider.
        when('/', {
            templateUrl: '/templates/login/index.html',
            controller: indexCtrl,
            reloadOnSearch: false
        }).
        when('/index', {
            templateUrl: '/templates/login/index.html',
            controller: indexCtrl,
            reloadOnSearch: false
        }).
        when('/admin', {
            templateUrl: '/templates/main/index.html',
            controller: mainCtrl,
            reloadOnSearch: false
        }).
        otherwise({
            redirectTo: '/'
        });

    // use the HTML5 History API
    $locationProvider.html5Mode(true);
});

app.run(['$anchorScroll', function($anchorScroll) {
    $anchorScroll.yOffset = 50;   // always scroll by 50 extra pixels
}]);