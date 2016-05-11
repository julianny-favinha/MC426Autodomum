'use strict';

angular.module('autodomun.clothes-line', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/clothes-line', {
    templateUrl: 'features/clothes-line/clothes-line.html',
    controller: 'ClothesLineController'
  });
}])

.controller('ClothesLineController', function($scope, $location) {
    $scope.home = function() {
        $location.path('/home');
    }
});