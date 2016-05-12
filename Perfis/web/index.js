'use strict';

angular.module('autodomun.index', ['ngRoute'])
.controller('IndexController', function($scope, $location) {
    $scope.isLoginPage = function() {
        return $location.path() == '/';
    }

    $scope.home = function() {
        $location.path('/home');
    }
});