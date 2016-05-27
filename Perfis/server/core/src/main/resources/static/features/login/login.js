'use strict';

angular.module('autodomun.login', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/', {
    templateUrl: 'features/login/login.html',
    controller: 'View1Ctrl'
  });
}])

.controller('View1Ctrl', function($scope, $location, $http) {
    $scope.login = function() {
        $http({
            method: 'POST',
            url: '/api/login',
            data: {
                username : $scope.username,
                senha : $scope.password
            }
        }).then(function successCallback(response) {
            if(response.status != 200 || response.data.sucesso != true) {
                $scope.error = true;
            } else {
                $location.path('/home');
            }
        }, function errorCallback(response) {
            $scope.error = true;
        });
    }
});