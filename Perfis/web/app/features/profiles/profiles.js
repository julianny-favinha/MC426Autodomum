'use strict';

angular.module('autodomun.profiles', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/profiles', {
    templateUrl: 'features/profiles/profiles.html',
    controller: 'ProfilesController'
  });
}])

.controller('ProfilesController', function($scope, $location, $anchorScroll) {
    $scope.view = function() {
        $location.path('/profile');
    }

    $scope.new = function () {
        $location.path('/profile/new');
    }

    $anchorScroll();
});