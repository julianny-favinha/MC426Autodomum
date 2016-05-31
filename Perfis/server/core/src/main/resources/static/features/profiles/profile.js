'use strict';

angular.module('autodomun.profile', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/profile', {
    templateUrl: 'features/profiles/profile.html',
    controller: 'ProfileController'
  });
}])

.controller('ProfileController', function($scope, $location, $http, $anchorScroll) {
    $scope.user = {};

    $scope.back = function() {
        $location.path("/profiles");
    }

    $scope.hasPermissao = function(idPermissao) {
        return $scope.user.permissoes.indexOf(idPermissao) >= 0;
    }

    $scope.togglePermissao = function(idPermissao) {
        var index = $scope.user.permissoes.indexOf(idPermissao)
        if(index < 0) {
            $scope.user.permissoes.push(idPermissao);
        } else {
            $scope.user.permissoes.splice(index, 1);
        }
    }

    $scope.save = function() {
        $http({
            method: 'PUT',
            url: '/api/usuario',
            data: $scope.user
        }).then(function successCallback(response) {
            if(response.status != 200) {
                $scope.error = true;
            } else {
                $location.path("profiles");
            }
        }, function errorCallback(response) {
            $scope.error = true;
        });
    }

    $scope.admin = true;
    $scope.notification = true;
    $scope.garden = true;
    $scope.audio = true;
    $scope.clothesLine = true;

    $anchorScroll();

    $http({
        method: 'GET',
        url: '/api/usuario/busca?username=' + $location.search()["username"],
    }).then(function successCallback(response) {
        if(response.status != 200) {
            $scope.error = true;
        } else {
            $scope.user = response.data;
        }
    }, function errorCallback(response) {
        $scope.error = true;
    });

    $http({
        method: 'GET',
        url: '/api/permissao',
    }).then(function successCallback(response) {
        if(response.status != 200) {
            $scope.error = true;
        } else {
            $scope.permissoes = response.data;
            console.log($scope.permissao);
            console.log(response);
        }
    }, function errorCallback(response) {
        $scope.error = true;
    });
});