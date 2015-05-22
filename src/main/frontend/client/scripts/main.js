'use strict';

require('angular');
require('angular-ui-router');
require('angular-bootstrap-npm')
//require('bootstrap');
require('angular-animate');

var app = angular.module('RakotulkkiApp',
    [
        'ui.router',
        'ui.calendar',
        'ui.bootstrap',
        'ngAnimate'
    ]
);

// directives
require('./directives/CustomerPanel').inject(app);
require('./directives/SessionsPanel').inject(app);
require('./directives/InvoicesPanel').inject(app);
require('./directives/ValidatedInput').inject(app);
require('./directives/ValidatedCheckbox').inject(app);
require('./directives/ValidatedDatePicker').inject(app);
require('./directives/ValidatedTimePicker').inject(app);

// services
require('./services/CustomerService').inject(app);
require('./services/SessionsService').inject(app);
require('./services/InvoiceService').inject(app);
require('./services/UserService').inject(app);

// controllers
require('./controllers/LoginController').inject(app);
require('./controllers/HomeController').inject(app);
require('./controllers/CustomerController').inject(app);
require('./controllers/CustomerInvoiceGenerationController').inject(app);
require('./controllers/SessionsController').inject(app);
require('./controllers/CalendarCtrl').inject(app);

app.config(function ($locationProvider, $stateProvider, $httpProvider, $urlRouterProvider) {

    $urlRouterProvider.otherwise("/index");

    $stateProvider
        .state('login', {
            url: '/login',
            templateUrl: 'login.html',
            controller: 'LoginController'
        })
        .state('index', {
            url: '/index',
            templateUrl: 'views/landing.html',
            controller: 'HomeController'
        })
        .state('customer', {
            url: '/customer/:id?',
            templateUrl: 'views/editcustomer.html',
            controller: 'CustomerController'
        })
        .state('generateCustomerInvoices', {
            url: '/customer/:id/invoices/generate',
            templateUrl: 'views/createdinvoices.html',
            controller: 'CustomerInvoiceGenerationController'
        })
        .state('session', {
            url: '/session/:id?',
            templateUrl: 'views/editsession.html'
        })
        .state('calendar', {
            url: '/calendar',
            templateUrl: 'views/calendar.html',
            controller: 'CalendarCtrl'
        });

    $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
});

/**
 * Custom filter for joining string arrays with a delimiter.
 */
app.filter('joinBy', function () {
    return function (input, delimiter) {
        var arr = input || [];
        var delim = delimiter || ',';
        return arr.join(delim);
    };
});


app.run();
