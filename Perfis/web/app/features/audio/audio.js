'use strict';

angular.module('autodomun.audio', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/audio', {
    templateUrl: 'features/audio/audio.html',
    controller: 'AudioController'
  });
}])

.controller('AudioController', function($scope, $location) {
    $scope.active = true;
});