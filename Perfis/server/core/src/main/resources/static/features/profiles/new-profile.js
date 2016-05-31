'use strict';

angular.module('autodomun.profile.new', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/profile/new', {
    templateUrl: 'features/profiles/new-profile.html',
    controller: 'NewProfileController'
  });
}])

.controller('NewProfileController', function($scope, $location, $http, $anchorScroll) {
    $scope.passwordDontMatch = false;
    $scope.user = {};
    $scope.user.permissoes = [];

    $scope.togglePermissao = function(idPermissao) {
        var index = $scope.user.permissoes.indexOf(idPermissao)
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

        $http({
            method: 'POST',
            url: '/api/signup',
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

    $scope.back = function() {
        $location.path('/profiles');
    }

    $anchorScroll();

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