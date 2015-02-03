'use strict';

exports.inject = function (app) {
    // require('./../directives/TestDirective').inject(app);
    app.controller('TestCtrl', exports.controller);
    return exports.controller;
};

exports.controller = function ($scope, $http) {
    $http.get('/customers').success(function (data) {
        $scope.customers = data;
    })
};
