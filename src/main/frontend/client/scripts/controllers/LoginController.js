'use strict';

exports.inject = function (app) {
    app.controller('LoginController', exports.controller);
    return exports.controller;
};

exports.controller = function ($rootScope, $scope, $http, $location) {

    var authenticate = function (credentials, callback) {

        var headers = credentials ? {
            authorization: "Basic "
            + btoa(credentials.username + ":" + credentials.password)
        } : {};

        $http.get('user', {headers: headers}).success(function (data) {
            if (data.name) {
                $rootScope.authenticated = true;
                $rootScope.userName = data.name;
            } else {
                $rootScope.authenticated = false;
            }
            callback && callback();
        }).error(function () {
            $rootScope.authenticated = false;
            callback && callback();
        });

    }

    authenticate();
    $scope.credentials = {};
    $scope.login = function () {
        authenticate($scope.credentials, function () {
            if ($rootScope.authenticated) {
                $location.path("/index");
                $scope.error = false;
            } else {
                $location.path("/login");
                $scope.error = true;
            }
        });
    };
};
