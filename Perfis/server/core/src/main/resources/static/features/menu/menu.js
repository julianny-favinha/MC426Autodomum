'use strict';

angular.module('autodomun.menu', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/home', {
    templateUrl: 'features/menu/menu.html',
    controller: 'View2Ctrl'
  });
}])

.controller('View2Ctrl', function($scope, $location, $http, $anchorScroll) {
    $scope.user = {};
    $scope.user.permissoes = [];;

    $scope.garden = function() {
        $location.path("/garden");
    }

    $scope.clothesLine = function() {
        $location.path("/clothes-line");
    }

    $scope.audio = function() {
        $location.path("/audio");
    }

    $scope.profiles = function() {
        $location.path("/profiles");
    }

    $scope.hasPermissao = function(permissaoId) {
        return $scope.user.permissoes.indexOf(permissaoId) >= 0;
    }

    $anchorScroll();

    $http({
        method: 'GET',
        url: '/api/usuario/logado',
    }).then(function successCallback(response) {
        if(response.status != 200) {
            $scope.error = true;
        } else {
            $scope.user = response.data;
        }
    }, function errorCallback(response) {
        $scope.error = true;
    });
});