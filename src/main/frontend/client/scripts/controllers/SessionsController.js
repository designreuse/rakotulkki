'use strict';

exports.inject = function (app) {
    app.controller('SessionsController', exports.controller);
    return exports.controller;
};

exports.controller = function ($scope, $location, CustomerService, SessionsService) {

    $scope.today = function () {
        $scope.dt = new Date();
    };
    $scope.today();

    // Disable weekend selection
    $scope.disabled = function (date, mode) {
        return ( mode === 'day' && ( date.getDay() === 0 || date.getDay() === 6 ) );
    };

    $scope.toggleMin = function () {
        $scope.minDate = $scope.minDate ? null : new Date();
    };
    $scope.toggleMin();

    $scope.open = function ($event) {
        $event.preventDefault();
        $event.stopPropagation();

        $scope.opened = true;
    };
    $scope.dateOptions = {
        formatYear: 'yy',
        startingDay: 1
    };

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
