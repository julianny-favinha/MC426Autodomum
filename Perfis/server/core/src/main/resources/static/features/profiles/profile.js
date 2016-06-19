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
    $scope.admin = false;

    $scope.back = function() {
        $location.path("/profiles");
    }

    $scope.toggleAdmin = function() {
        if($scope.admin) {
            $scope.user.permissoes = $scope.permissoes.map(function(permission) {
                return permission.id;
            });
        } else {
            $scope.user.permissoes = [];
            //TODO show dialog asking to add permissions
        }
    }

    function permissionIndex(idPermissao) {
        return $scope.user.permissoes.indexOf(idPermissao);
    }

    $scope.isPermissionSelected = function(idPermissao) {
        return $scope.user.permissoes[permissionIndex(idPermissao)];
    }

    $scope.togglePermissao = function(idPermissao) {
        var index = permissionIndex(idPermissao);
        if(index < 0) {
            $scope.user.permissoes.push(idPermissao);
        } else {
            $scope.user.permissoes.splice(index, 1);
        }

        if($scope.user.permissoes.length == $scope.permissoes.length) {
            $scope.admin = true;
        } else {
            $scope.admin = false;
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

    $anchorScroll();

    permissionService.getPermissions()
        .then(function successCallback(response) {
            $scope.permissoes = response.data;
        }, function errorCallback(response) {
            $scope.error = true;
        });

    userService.getByUsername($location.search()["username"])
        .then(function successCallback(response) {
            $scope.user = response.data;
            $scope.admin = $scope.user.permissoes.length == 4; //FIXME
        }, function errorCallback(response) {
            $scope.error = true;
        });

});