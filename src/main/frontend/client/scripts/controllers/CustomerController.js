'use strict';

exports.inject = function (app) {
    app.controller('CustomerController', exports.controller);
    return exports.controller;
};

exports.controller = function ($scope, $location, CustomerService) {

    $scope.customer = {};

    $scope.listCustomers = function () {
        CustomerService.list().success(function (data) {
            $scope.customers = data;
        })
    };

    $scope.save = function () {
        CustomerService.save($scope.customer).success(function (data) {
            $location.path("/index");
        });
    };

};
