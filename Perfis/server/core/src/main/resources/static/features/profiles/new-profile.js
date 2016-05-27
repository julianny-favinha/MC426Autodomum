'use strict';

angular.module('autodomun.profile.new', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/profile/new', {
    templateUrl: 'features/profiles/new-profile.html',
    controller: 'NewProfileController'
  });
}])

.controller('NewProfileController', function($scope, $location, $http, $anchorScroll) {
    $scope.user = {};
    $scope.passwordDontMatch = false;

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
});