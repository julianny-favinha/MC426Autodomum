angular.module('autodomun.services.user', ['ngRoute'])

.factory('userService', function($http) {
    function UserService () {
        this.getUsers = function() {
            return $http({
                method: 'GET',
                url: '/api/usuario',
            });
        }

        this.save = function(user) {
            return $http({
                method: 'PUT',
                url: '/api/usuario',
                data: user
            });
        }

        this.getByUsername = function(username) {
            return $http({
                method: 'GET',
                url: '/api/usuario/busca?username=' + username,
            });
        }

        this.create = function(user) {
            return $http({
                method: 'POST',
                url: '/api/signup',
                data: user
            });
        }
    }

    return new UserService(); 
});