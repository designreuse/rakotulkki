'use strict';

exports.inject = function (app) {
    app.directive('customerPanel', exports.directive);
    return exports.directive;
};

/**
 * Custom directive for
 */
exports.directive = function () {
    return {
        restrict: 'E',
        templateUrl: "/views/partials/customerPanel.html",
        replace: 'true',
        link: function (scope, element, attrs, ctrl) {
            console.log(scope);
        }
    };
};

