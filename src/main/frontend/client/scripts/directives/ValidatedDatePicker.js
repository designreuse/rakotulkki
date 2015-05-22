'use strict';

exports.inject = function (app) {
    app.directive('validatedDatePicker', exports.directive);
    return exports.directive;
};


/**
 * Custom directive for validated text inputs.
 */
exports.directive = function () {
    return {
        restrict: 'E',
        templateUrl: "/views/partials/validatedDatePicker.html",
        require: ['^form', 'ngModel'],
        replace: 'true',
        scope: {
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

            /**
             * Open popup date picker
             * @param $event
             */
            scope.open = function ($event) {
                $event.preventDefault();
                $event.stopPropagation();
                scope.opened = true;
                ctrl[1].$setValidity('server', true);
                scope.ctrl.errors = undefined;
            };

            // Clear validation messages when user modifies the model
            element.on('change keyup', function () {
                scope.$apply(function () {
                    ctrl[1].$setValidity('server', true);
                    scope.ctrl.errors = undefined;
                });
            });
        }
    };
};

