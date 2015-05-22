'use strict';

exports.inject = function (app) {
    app.directive('validatedCheckbox', exports.directive);
    return exports.directive;
};


/**
 * Custom directive for validated text inputs.
 */
exports.directive = function () {
    return {
        restrict: 'E',
        templateUrl: "/views/partials/validatedCheckbox.html",
        require: ['^form', 'ngModel'],
        replace: 'true',
        scope: {
            // Header shown for the input
            header: '@',
            // Label shown for the input
            label: '@',
            // Property name. Used for registering the input and validation data in the form controller.
            propertyName: "@",
            // Defined model.
            ngModel: '=',
            disabled: "="
        },
        link: function (scope, element, attrs, ctrl) {
            // Define the form element used to access input -validity
            // ctrl[0] is the parent form
            scope.ctrl = ctrl[0][scope.propertyName];

            // Clear validation messages when user modifies the model
            element.on('change keyup', function () {
                scope.$apply(function () {
                    ctrl[1].$setValidity('server', true);
                });
            });
        }
    };
};
