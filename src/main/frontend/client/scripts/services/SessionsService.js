'use strict';

exports.inject = function (app) {
    app.factory('SessionsService', exports.service);
    return exports.service;
};

exports.service = function ($http) {
    return {
        new: function () {
            return $http.get('/sessions/new');
        },
        load: function (id) {
            return $http.get('/sessions/' + id);
        },
        list: function () {
            return $http.get('/sessions/');
        },
        listEvents: function () {
            return $http.get('/sessions/asEvents');
        },
        save: function (session) {
            return $http.post('/sessions/', session);
        },
        update: function (session) {
            return $http.put('/sessions/' + session.id, session);
        }
    }
};

