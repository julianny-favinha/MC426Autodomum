'use strict';

angular.module('autodomun.profile', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/profile', {
    templateUrl: 'features/profiles/profile.html',
    controller: 'ProfileController'
  });
}])

.controller('ProfileController', function($scope, $location, userService, permissionService, $anchorScroll) {
    $scope.user = {};
    $scope.user.permissoes = [];

    $scope.back = function() {
        $location.path("/profiles");
    }

    $scope.permissionIndex = function(idPermissao) {
        return $scope.user.permissoes.indexOf(idPermissao);
    }

    $scope.togglePermissao = function(idPermissao) {
        var index = $scope.permissionIndex(idPermissao);
        if(index < 0) {
            $scope.user.permissoes.push(idPermissao);
        } else {
            $scope.user.permissoes.splice(index, 1);
        }
    }

    $scope.save = function() {
        userService.save($scope.user)
            .then(function successCallback(response) {
                $location.path("profiles");
            }, function errorCallback(response) {
                $scope.error = true;
            });
    }

    $scope.admin = true; //TODO

    $anchorScroll();

    userService.getByUsername($location.search()["username"])
        .then(function successCallback(response) {
            $scope.user = response.data;
        }, function errorCallback(response) {
            $scope.error = true;
        });

    permissionService.getPermissions()
        .then(function successCallback(response) {
            $scope.permissoes = response.data;
        }, function errorCallback(response) {
            $scope.error = true;
        });

});