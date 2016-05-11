'use strict';

angular.module('autodomun.clothes-line', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/clothes-line', {
    templateUrl: 'features/clothes-line/clothes-line.html',
    controller: 'ClothesLineController'
  });
}])

.controller('ClothesLineController', function($scope, $location, weatherService) {
    $scope.home = function() {
        $location.path('/home');
    }

    $scope.weather = weatherService.getWeather();
    $scope.today = new Date();
});