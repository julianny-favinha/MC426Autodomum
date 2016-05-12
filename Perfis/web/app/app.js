'use strict';

// Declare app level module which depends on views, and components
angular.module('autodomun', [
  'ngRoute',
  'autodomun.index',
  'autodomun.login',
  'autodomun.menu',
  'autodomun.garden',
  'autodomun.audio',
  'autodomun.clothes-line',
  'autodomun.profiles',
  'autodomun.profile',
  'autodomun.profile.new',
  'autodomun.services',
  'autodomun.components',
  'autodomun.version',

  'uiSwitch'
])
.config(['$routeProvider', function($routeProvider) {
  $routeProvider.otherwise({redirectTo: '/'});
}]);
