/**
 * Custom directive for
 */
cmbpAdmin.directive('customerPanel', function () {
    return {
        restrict: 'E',
        templateUrl: "/views/partials/customerPanel.html",
        require: ['ngModel'],
        replace: 'true',
        scope: {
            // Defined model.
            ngModel: '='
        },
        link: function (scope, element, attrs, ctrl) {

        }
    };
});

