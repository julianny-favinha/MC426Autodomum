angular.module('autodomun.services.garden', ['ngRoute'])

.factory('gardenService', function($http) {
    function GardenService () {
        this.getHistory = function() {
            return $http({
                method: 'GET',
                url: '/api/jardim/historico'
            });
        }
    }

    return new GardenService();
});