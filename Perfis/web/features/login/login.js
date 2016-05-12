'use strict';

angular.module('autodomun.login', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/', {
    templateUrl: 'features/login/login.html',
    controller: 'View1Ctrl'
  });
}])

.controller('View1Ctrl', function($scope, $location) {
    $scope.home = function() {
        $location.path('/home');
    }
});