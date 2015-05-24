'use strict';

require('angular');
require('angular-ui-router');
require('angular-bootstrap-npm')
require('angular-animate');
require('angular-translate');

var app = angular.module('RakotulkkiApp',
    [
        'ui.router',
        'ui.calendar',
        'ui.bootstrap',
        'ngAnimate',
        'pascalprecht.translate'
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
require('./directives/ValidatedRadioGroup').inject(app);
require('./directives/ValidatedCustomerSearch').inject(app);

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
require('./controllers/CalendarController').inject(app);

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
            controller: 'CalendarController'
        });

    $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
});

app.config(['$translateProvider', function ($translateProvider) {
    $translateProvider.translations('fi', {
        // Session types
        'EXPLORATORY': 'Tutustumiskäynti',
        'THERAPY': 'Terapiakäynti',
        // Edit session -template
        'view.session.create.header': 'Lisää uusi käynti',
        'view.session.edit.header': 'Muokkaa käyntiä',
        'ctrl.customerSelect.header': 'Valitse asiakas',
        'ctrl.customerSelect.label': 'Hae asiakas nimellä',
        'customer': 'Asiakas',
        'view.session.repeated.header': 'Toistuvuus',
        'view.session.repeated.label': 'käynti on toistuva',
        'view.session.type.header': 'Käynnin tyyppi',
        'view.session.date.label': 'Alkaen pvm',
        'view.session.dateUntil.label': 'Päättyen pvm',
        'view.session.startTime.label': 'Alkamisaika klo',
        'view.session.endTime.label': 'Päättymisaika klo',
        'view.session.price.label': 'Hinta',
        'view.session.compensation.header': 'Kela-tuki',
        'view.session.compensation.label': 'veloita Kela-korvaus tälle käynnille',
    });

    $translateProvider.preferredLanguage('fi');
}]);

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
