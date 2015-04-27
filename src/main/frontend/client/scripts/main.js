'use strict';

require('angular');
require('angular-ui-router');
require('bootstrap');
require('angular-animate');

var app = angular.module('RakotulkkiApp',
    [
        'ui.router',
        'ui.calendar',
        'mgcrea.ngStrap',
        'ngAnimate'
    ]
);

// directives
require('./directives/CustomerPanel').inject(app);
require('./directives/SessionsPanel').inject(app);
require('./directives/InvoicesPanel').inject(app);
require('./directives/ValidatedInput').inject(app);

// services
require('./services/CustomerService').inject(app);
require('./services/SessionsService').inject(app);
require('./services/InvoiceService').inject(app);
require('./services/UserService').inject(app);

app.config(function ($locationProvider, $stateProvider, $httpProvider) {

    $stateProvider
        .state('login', {
            url: '/login',
            templateUrl: 'login.html',
            controller: require('./controllers/LoginController').inject(app)
        })
        .state('index', {
            url: '/index',
            templateUrl: 'views/landing.html',
            controller: require('./controllers/HomeController').inject(app)
        })
        .state('customer', {
            url: '/customer/:id?',
            templateUrl: 'views/editcustomer.html',
            controller: require('./controllers/CustomerController').inject(app)
        })
        .state('generateCustomerInvoices', {
            url: '/customer/:id/invoices/generate',
            templateUrl: 'views/createdinvoices.html',
            controller: require('./controllers/CustomerInvoiceGenerationController').inject(app)
        })
        .state('session', {
            url: '/session/:id?',
            templateUrl: 'views/editsession.html',
            controller: require('./controllers/SessionsController').inject(app)
        })
        .state('calendar', {
            url: '/calendar',
            templateUrl: 'views/calendar.html',
            controller: require('./controllers/CalendarCtrl').inject(app)
        });

    $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
});

app.run();
