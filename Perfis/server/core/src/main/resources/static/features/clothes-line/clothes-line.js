'use strict';

angular.module('autodomun.clothes-line', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/clothes-line', {
    templateUrl: 'features/clothes-line/clothes-line.html',
    controller: 'ClothesLineController'
  });
}])

.controller('ClothesLineController', function($scope, weatherService, awningService, $anchorScroll, $location) {
	$scope.history = [];
    $scope.command = {
        estendido : false,
        automatico : false,
        toldo: 'VARAL'
    };

    $scope.weather = weatherService.getWeather();
    $scope.today = new Date();

    $anchorScroll();

    $scope.showDateInLocalTimezone = function(datetimeString) {
        var dateString = datetimeString.substr(0, datetimeString.indexOf(" "));
        var timeString = datetimeString.substr(datetimeString.indexOf(" ") + 1, datetimeString.length);

        var dateArray = dateString.split("/");
        var day = dateArray[0];
        var month = dateArray[1];
        var year = dateArray[2];

        var timeArray = timeString.split(":");
        var hour = timeArray[0];
        var minute = timeArray[1];

        var date = new Date(year, month, day, hour, minute);

        var newDate = new Date(date.getTime()+date.getTimezoneOffset()*60*1000);

        var offset = date.getTimezoneOffset() / 60;
        var hours = date.getHours();

        newDate.setHours(hours - offset);

        return newDate;  
    }

    $scope.changeState = function() {
    	awningService.sendCommand($scope.command)
            .then(function successCallback(response) {
                $location.path('/home'); //FIXME
            }, function errorCallback(response) {
                $scope.error = true;
            });
    };

    $scope.back = function() {
        $location.path('/home');
    }

    awningService.getHistory('VARAL')
        .then(function successCallback(response) {
            $scope.history = response.data;
            var currentState = $scope.history[0];
            $scope.command.estendido = currentState.estendido;
            $scope.command.automatico = currentState.automatico;
        }, function errorCallback(response) {
            $scope.error = true;
        });
});