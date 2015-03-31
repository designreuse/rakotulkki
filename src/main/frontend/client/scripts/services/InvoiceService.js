'use strict';

exports.inject = function (app) {
    app.factory('InvoiceService', exports.service);
    return exports.service;
};

exports.service = function ($http) {
    return {
        load: function (id) {
            return $http.get('/invoices/' + id);
        },
        list: function () {
            return $http.get('/invoices/');
        },
        generateAll: function () {
            return $http.get('/invoices/generateAll');
        },
        generateForCustomer: function (customerId) {
            return $http.get('/invoices/customer/' + customerId + "/generate");
        },
        getPendingCount: function (customerId) {
            return $http.get('/invoices/customer/' + customerId + "/pending");
        }
    }
};

