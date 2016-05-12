'use strict';

angular.module('autodomun.menu', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/home', {
    templateUrl: 'features/menu/menu.html',
    controller: 'View2Ctrl'
  });
}])

.controller('View2Ctrl', function($scope, $location, $anchorScroll) {
    $scope.garden = function() {
        $location.path("/garden");
    }

    $scope.clothesLine = function() {
        $location.path("/clothes-line");
    }

    $scope.audio = function() {
        $location.path("/audio");
    }

    $scope.profiles = function() {
        $location.path("/profiles");
    }

    $anchorScroll();
});