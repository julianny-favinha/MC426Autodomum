'use strict';

angular.module('autodomun.profile.new', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/profile/new', {
    templateUrl: 'features/profiles/new-profile.html',
    controller: 'NewProfileController'
  });
}])

.controller('NewProfileController', function($scope, $location) {
    $scope.profiles = function() {
        $location.path('/profiles');
    }
});