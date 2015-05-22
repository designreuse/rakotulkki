'use strict';

exports.inject = function (app) {
    app.factory('FormErrorService', exports.service);
    return exports.service;
};

exports.service = function () {
    return {
        processErrors: function (data, form) {
            var service = this;
            if (data.constructor === Array) {
                data.forEach(function (error) {
                    // parse the property name from path after .arg0.
                    var path = error.path.split(/\.arg[0-9]\./);
                    var propName = path.length == 2 ? path[1] : path[0];
                    if (propName != error.path) {
                        service.setErrorForField(form, propName, error.message);
                    } else {
                        // Root bean is empty. Fix controller.
                    }
                });
            } else {
                // Handle conversion error case
                for (var prop in data.violations) {
                    // TODO proper conversion error message per type
                    service.setErrorForField(form, prop, "Invalid");
                }
            }
        },
        setErrorForField: function (form, propName, msg) {
            if (form[propName]) {
                // set field invalid
                form[propName].$setValidity('server', false);
                // set error to scope
                form[propName].errors = [msg];
            } else {
                // Field not available in UI form. Ingore
            }
        }
    };
};
