'use strict';

exports.inject = function (app) {
    app.factory('UserService', exports.service);
    return exports.service;
};

exports.service = function ($http) {
    return {}
};

