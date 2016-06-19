'use strict';

angular.module('autodomun.garden', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/garden', {
    templateUrl: 'features/garden/garden.html',
    controller: 'GardenController'
  });
}])

.filter('temp', function($filter) {
    return function(input, precision) {
        if (!precision) {
            precision = 1;
        }
        var numberFilter = $filter('number');
        return numberFilter(input, precision) + '\u00B0C';
    };
})

.controller('GardenController', function($scope, weatherService, awningService, $anchorScroll, $location) {
    $scope.awningCommand = {
        estendido : false,
        automatico : false,
        toldo: 'JARDIM'
    };

    $scope.weather = weatherService.getWeather();
    $scope.today = new Date();

    $anchorScroll();

    $scope.changeAwningState = function() {
        awningService.sendCommand($scope.awningCommand)
            .then(function successCallback(response) {
                $location.path('/home'); //FIXME
            }, function errorCallback(response) {
                $scope.error = true;
            });
    };

    $scope.back = function() {
        $location.path('/home');
    }

    awningService.getHistory('JARDIM')
        .then(function successCallback(response) {
            $scope.history = response.data; //Se quiser exibir o historico ta aqui
            var currentState = $scope.history[0];
            $scope.awningCommand.estendido = currentState.estendido;
            $scope.awningCommand.automatico = currentState.automatico;
        }, function errorCallback(response) {
            $scope.error = true;
        });

});