angular.module('autodomun.services.permission', ['ngRoute'])

.factory('permissionService', function($http) {
    function PermissionService () {
        this.getPermissions = function() {
            return $http({
                method: 'GET',
                url: '/api/permissao',
            });
        }
    }

    return new PermissionService();
});