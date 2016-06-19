'use strict';

angular.module('autodomun.profile.new', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/profile/new', {
    templateUrl: 'features/profiles/new-profile.html',
    controller: 'NewProfileController'
  });
}])

.controller('NewProfileController', function($scope, $location, userService, permissionService, $anchorScroll) {
    $scope.passwordDontMatch = false;
    $scope.user = {};
    $scope.user.permissoes = [];

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

    $scope.create = function() {
        $scope.passwordDontMatch = false;

        if($scope.user.senha != $scope.user.confirmaSenha) {
            $scope.passwordDontMatch = true;
            return;
        }

        userService.create($scope.user)
            .then(function successCallback(response) {
                $location.path("profiles");
            }, function errorCallback(response) {
                $scope.error = true;
            });
    }

    $scope.back = function() {
        $location.path('/profiles');
    }

    $anchorScroll();

    permissionService.getPermissions()
        .then(function successCallback(response) {
            $scope.permissoes = response.data;
        }, function errorCallback(response) {
            $scope.error = true;
        });
});