'use strict';

exports.inject = function (app) {
    app.controller('HomeController', exports.controller);
    return exports.controller;
};

exports.controller = function ($scope, CustomerService) {

    $scope.list = function () {
        CustomerService.list().success(function (data) {
            $scope.customers = data;
        })
    };

    $scope.list();

};
