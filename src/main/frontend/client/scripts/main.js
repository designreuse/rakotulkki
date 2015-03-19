'use strict';

require('angular');
require('angular-ui-router');
require('bootstrap');

var app = angular.module('RakotulkkiApp',
    [
        'ui.router',
        'ui.calendar',
        'mgcrea.ngStrap.modal',
        'mgcrea.ngStrap.aside',
        'mgcrea.ngStrap.tooltip'
    ]
);

// directives
require('./directives/CustomerPanel').inject(app);

// services
require('./services/CustomerService').inject(app);

app.config(function ($locationProvider, $stateProvider) {

    $stateProvider
        .state('index', {
            url: '/index',
            templateUrl: 'views/landing.html',
            controller: require('./controllers/HomeController').inject(app)
        })
        .state('calendar', {
            url: '/calendar',
            templateUrl: 'views/calendar.html',
            controller: require('./controllers/CalendarCtrl').inject(app)
        });
});

app.run();
