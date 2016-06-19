'use strict';

angular.module('autodomun.profiles', ['ngRoute'])

.config(function($routeProvider) {
  $routeProvider.when('/profiles', {
    templateUrl: 'features/profiles/profiles.html',
    controller: 'ProfilesController'
  });
})

.controller('ProfilesController', function($scope, $location, userService, $anchorScroll) {
    $scope.users = [];
    userService.getUsers()
        .then(function successCallback(response) {
            $scope.users = response.data;
        }, function errorCallback(response) {
            $scope.error = true;
        });

    $scope.isAdmin = function(user) {
        return user.permissoes.length == 4; //FIXME
    }

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
});