angular.module('autodomun.services.awning', ['ngRoute'])

.factory('awningService', function($http) {
    function AwningService () {
        this.getHistory = function(awning) {
            return $http({
                method: 'GET',
                url: '/api/toldo/historico?toldo=' + awning
            });
        }

        this.sendCommand = function(command) {
            return $http({
                method: 'POST',
                url: '/api/toldo/comando',
                data: command
            })
        }
    }

    return new AwningService();
});