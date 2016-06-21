'use strict';

angular.module('autodomun.audio', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/audio', {
    templateUrl: 'features/audio/audio.html',
    controller: 'AudioController'
  });
}])

.controller('AudioController', function($scope, $http, $location, $anchorScroll) {
    $scope.active = true;
    $scope.user = {};
    $scope.artista = {};
    $scope.command = {
    	artista : 'radiohead',
    	audio : 'PLAY'
    };

    $scope.back = function() {
        $location.path('/home');
    }
    
    $http({
        method: 'GET',
        url: '/api/usuario/logado',
    }).then(function successCallback(response) {
        if(response.status != 200) {
            $scope.error = true;
        } else {
            $scope.user = response.data;
            $http({
    			method: 'GET',
    			url: '/api/audio/artista?username=' + $scope.user.username,
   			}).then(function successCallback(response2){
   				if(response2.status != 200) {
            		$scope.error = true;
        		} else {
            		$scope.artista = response2.data;
            		$scope.command.artista = $scope.artista.artista;
            	}
   			}, function errorCallback(response2){
   				$scope.error = true;
   			});
        }
    }, function errorCallback(response) {
        $scope.error = true;
    });
    
    $scope.sendAction = function() {
       $http({
            method: 'POST',
            url: '/api/audio/comando',
            data: $scope.command
        }).then(function successCallback(response) {
            if(response.status != 200 || response.data.sucesso != true) {
                $scope.error = true;
            } else {
                $location.path('/audio');
            }
        }, function errorCallback(response) {
            $scope.error = true;
        });
    }
    
    $scope.artistaNew = function() {
    	$location.path('/audio/artista');
    }
    
    $anchorScroll();
});