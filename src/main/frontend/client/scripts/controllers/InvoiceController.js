'use strict';

exports.inject = function (app) {
    app.controller('InvoiceController', exports.controller);
    return exports.controller;
};

exports.controller = function ($scope, $location, $stateParams, CustomerService, InvoiceService) {

    $scope.listInvoices = function () {
        InvoiceService.list().success(function (data) {
            $scope.invoices = data;
        })
    };
};
