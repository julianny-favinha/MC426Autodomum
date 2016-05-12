'use strict';

angular.module('autodomun.profile', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/profile', {
    templateUrl: 'features/profiles/profile.html',
    controller: 'ProfileController'
  });
}])

.controller('ProfileController', function($scope, $location) {
    $scope.admin = true;
    $scope.notification = true;
    $scope.garden = true;
    $scope.audio = true;
    $scope.clothesLine = true;
});