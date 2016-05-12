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

.controller('GardenController', function($scope, weatherService, $anchorScroll) {
    $scope.weather = weatherService.getWeather();
    $scope.today = new Date();
    $scope.active = true;

    $anchorScroll();
});