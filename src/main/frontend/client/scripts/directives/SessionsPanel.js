'use strict';

exports.inject = function (app) {
    app.directive('sessionsPanel', exports.directive);
    return exports.directive;
};

/**
 * Custom directive for
 */
exports.directive = function () {
    return {
        restrict: 'E',
        templateUrl: "/views/partials/sessionsPanel.html",
        replace: 'true',
        link: function (scope, element, attrs, ctrl) {

        }
    };
};

