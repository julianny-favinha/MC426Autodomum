'use strict';

angular.module('autodomun.garden', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/garden', {
    templateUrl: 'features/garden/garden.html',
    controller: 'GardenController'
  });
}])

.filter('temp', function($filter) {
    return function(input, precision) {
        if (!precision) {
            precision = 1;
        }
        var numberFilter = $filter('number');
        return numberFilter(input, precision) + '\u00B0C';
    };
})

.controller('GardenController', function($scope, $location, weatherService) {
    $scope.home = function() {
        $location.path('/home');
    }

    $scope.weather = weatherService.getWeather();
    $scope.today = new Date();

    $scope.categories = [
        {
            "category": [
                {
                    "label": "Jan"
                },
                {
                    "label": "Feb"
                },
                {
                    "label": "Mar"
                },
                {
                    "label": "Apr"
                },
                {
                    "label": "May"
                },
                {
                    "label": "Jun"
                },
                {
                    "label": "Jul"
                },
                {
                    "label": "Aug"
                },
                {
                    "label": "Sep"
                },
                {
                    "label": "Oct"
                },
                {
                    "label": "Nov"
                },
                {
                    "label": "Dec"
                }
            ]
        }
    ];

    $scope.dataset = [
        {
            "seriesname": "2013",
            "data": [
                {
                    "value": "22400"
                },
                {
                    "value": "24800"
                },
                {
                    "value": "21800"
                },
                {
                    "value": "21800"
                },
                {
                    "value": "24600"
                },
                {
                    "value": "27600"
                },
                {
                    "value": "26800"
                },
                {
                    "value": "27700"
                },
                {
                    "value": "23700"
                },
                {
                    "value": "25900"
                },
                {
                    "value": "26800"
                },
                {
                    "value": "24800"
                }
            ]
        },
        {
            "seriesname": "2012",
            "data": [
                {
                    "value": "10000"
                },
                {
                    "value": "11500"
                },
                {
                    "value": "12500"
                },
                {
                    "value": "15000"
                },
                {
                    "value": "16000"
                },
                {
                    "value": "17600"
                },
                {
                    "value": "18800"
                },
                {
                    "value": "19700"
                },
                {
                    "value": "21700"
                },
                {
                    "value": "21900"
                },
                {
                    "value": "22900"
                },
                {
                    "value": "20800"
                }
            ]
        }
    ];
});