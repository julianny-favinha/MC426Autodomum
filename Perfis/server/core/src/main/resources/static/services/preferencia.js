angular.module('autodomun.services.preferencia', ['ngRoute'])

.factory('preferenciaService', function($http) {
    function UserService () {
        this.create = function(preferencia) {
            return $http({
                method: 'POST',
                url: '/api/audio/artista/new',
                data: preferencia
            });
        }
    }

    return new UserService(); 
});