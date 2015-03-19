'use strict';

exports.inject = function (app) {
    app.factory('CustomerService', exports.service);
    return exports.service;
};

exports.service = function ($http) {
    return {
        load: function (id) {
            return $http.get('/customers/' + id);
        },
        list: function () {
            return $http.get('/customers/');
        },
        save: function (customer) {
            return $http.post('/customers', customer);
        },
        update: function (customer) {
            return $http.put('/customers/' + customer.id, customer);
        }
    }
};

