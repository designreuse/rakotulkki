'use strict';

exports.inject = function (app) {
    app.controller('CustomerController', exports.controller);
    return exports.controller;
};

exports.controller = function ($scope, $location, $stateParams, InvoiceService) {

    $scope.generate = function (customerId) {
        InvoiceService.generateForCustomer(customerId).success(function (data) {
            $scope.invoices = data;
        });
    };

    $scope.generate($stateParams.id);

};
