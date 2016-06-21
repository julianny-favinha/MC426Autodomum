'use strict';

angular.module('autodomun.artista', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/audio/artista', {
    templateUrl: 'features/audio/artista.html',
    controller: 'PreferenciasController'
  });
}])

.controller('PreferenciasController', function($scope, $http, preferenciaService, $location, $anchorScroll) {
    $scope.active = true;
    $scope.user = {};
  	$scope.preferencia = {
  		id : 1, 
  		nome : 'br',
  		artista: ''
  	};
  	
  	$http({
        method: 'GET',
        url: '/api/usuario/logado',
    }).then(function successCallback(response) {
        if(response.status != 200) {
            $scope.error = true;
        } else {
            $scope.user = response.data;
            $scope.preferencia.nome = $scope.user.username;
        }
    }, function errorCallback(response) {
        $scope.error = true;
    });
  	
  	
    $scope.create = function() {
    	preferenciaService.create($scope.preferencia)
            .then(function successCallback(response) {
                $location.path("/audio");
            }, function errorCallback(response) {
                $scope.error = true;
            });
    }
    
    $scope.back = function() {
        $location.path('/audio');
    }
    
    $anchorScroll();
});