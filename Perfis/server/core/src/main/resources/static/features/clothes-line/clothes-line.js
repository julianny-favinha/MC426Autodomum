'use strict';

angular.module('autodomun.clothes-line', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/clothes-line', {
    templateUrl: 'features/clothes-line/clothes-line.html',
    controller: 'ClothesLineController'
  });
}])

.controller('ClothesLineController', function($scope, weatherService, $http, $anchorScroll, $location) {
	$scope.toldo = [];
	$scope.fechado = true;
	$scope.automatico = true;
    $scope.weather = weatherService.getWeather();
    $scope.today = new Date();
    $scope.active = true;
    $anchorScroll();
    $scope.changeToldo = function() {
    	$scope.automatico = !($scope.automatico);
    };
    $scope.changeAutomatico = function() {
    	$scope.fechado = !($scope.fechado);
    };
    $scope.changeState = function() {
        console.log($scope.fechado);
        console.log($scope.automatico);
    	$http({
            method: 'POST',
            url: '/api/toldo/comando',
            data: {
            	estendido : $scope.fechado,
            	automatico : $scope.automatico,
            	toldo : 'VARAL'
            }
        }).then(function successCallback(response) {
            if(response.status != 200) {
                $scope.error = true;
            } else {
                
            }
        }, function errorCallback(response) {
            $scope.error = true;
        });

        $location.path('/home');
    };
    $http({
        method: 'GET',
        url: '/api/toldo/historico',
        params: {
            toldo : 'VARAL'
        }
    }).then(function successCallback(response) {
        if(response.status != 200) {
            $scope.error = true;
        } else {
            $scope.toldo = response.data;
        }
    }, function errorCallback(response) {
        $scope.error = true;
    });
});