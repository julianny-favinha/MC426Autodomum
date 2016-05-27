'use strict';

angular.module('autodomun.index', ['ngRoute'])
.controller('IndexController', function($scope, $http, $location) {
    $scope.isLoginPage = function() {
        return $location.path() == '/';
    }

    $scope.home = function() {
        $location.path('/home');
    }

    $scope.logout = function() {
        $http({
            method: 'GET',
            url: '/api/logout',
        }).then(function successCallback(response) {
            if(response.status != 200) {
                $scope.error = true;
            } else {
                $location.path('/');
            }
        }, function errorCallback(response) {
            $scope.error = true;
        });
    }
});