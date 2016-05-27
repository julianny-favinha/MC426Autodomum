'use strict';

angular.module('autodomun.profiles', ['ngRoute'])

.config(function($routeProvider) {
  $routeProvider.when('/profiles', {
    templateUrl: 'features/profiles/profiles.html',
    controller: 'ProfilesController'
  });
})

.controller('ProfilesController', function($scope, $location, $http, $anchorScroll) {
    $scope.users = [];

    $scope.view = function(user) {
        $location.path('/profile').search("username", user.username);
    }

    $scope.back = function() {
        $location.path('/home');
    }

    $scope.new = function () {
        $location.path('/profile/new');
    }

    $anchorScroll();

    $http({
        method: 'GET',
        url: '/api/usuario',
    }).then(function successCallback(response) {
        if(response.status != 200) {
            $scope.error = true;
        } else {
            $scope.users = response.data;
        }
    }, function errorCallback(response) {
        $scope.error = true;
    });
});