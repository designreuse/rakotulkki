'use strict';

exports.inject = function (app) {
    app.directive('invoicesPanel', exports.directive);
    return exports.directive;
};

/**
 * Custom directive for
 */
exports.directive = function () {
    return {
        restrict: 'E',
        templateUrl: "/views/partials/invoicesPanel.html",
        replace: 'true',
        scope: {
            // Label shown for the input
            label: '@',
            // Defined model.
            invoices: '='
        },
        link: function (scope, element, attrs, ctrl) {

        }
    };
};

