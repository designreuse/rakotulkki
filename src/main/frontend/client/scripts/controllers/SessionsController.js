'use strict';

exports.inject = function (app) {
    app.controller('SessionsController', exports.controller);
    return exports.controller;
};

exports.controller = function ($scope, $location, CustomerService, SessionsService) {

    $scope.session = {};

    $scope.listCustomers = function () {
        CustomerService.list().success(function (data) {
            $scope.customers = data;
        })
    };

    $scope.listSessions = function () {
        SessionsService.list().success(function (data) {
            $scope.sessions = data;
        })
    };

    $scope.listCustomers();

    $scope.save = function () {
        console.log($scope.session);
        SessionsService.save($scope.session).success(function (data) {
            $location.path("/index");
        });
    };
};
