'use strict';

require('angular');
require('angular-ui-router');
require('bootstrap');

var app = angular.module('RakotulkkiApp', ['ui.router']);

app.config(function ($locationProvider, $stateProvider) {

    $locationProvider.html5Mode(true);

    $stateProvider
        .state('home', {
            url: '/customers',
            templateUrl: 'views/home.html',
            controller: require('./controllers/CustomerCtrl').inject(app)
        });

});

app.run();
