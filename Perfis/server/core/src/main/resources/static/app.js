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
  
  'autodomun.services.weather',
  'autodomun.services.user',
  'autodomun.services.permission',
  'autodomun.services.awning',
  
  'autodomun.components',
  'autodomun.version',

  'uiSwitch',
  'angular-loading-bar',
  'ngAnimate'
])
.config(function($routeProvider, cfpLoadingBarProvider) {
  $routeProvider.otherwise({redirectTo: '/'});
  cfpLoadingBarProvider.spinnerTemplate = 
      '<div class="sk-fading-circle">' + 
        '<div class="sk-circle1 sk-circle"></div>' +
        '<div class="sk-circle2 sk-circle"></div>' +
        '<div class="sk-circle3 sk-circle"></div>' +
        '<div class="sk-circle4 sk-circle"></div>' +
        '<div class="sk-circle5 sk-circle"></div>' +
        '<div class="sk-circle6 sk-circle"></div>' +
        '<div class="sk-circle7 sk-circle"></div>' +
        '<div class="sk-circle8 sk-circle"></div>' +
        '<div class="sk-circle9 sk-circle"></div>' +
        '<div class="sk-circle10 sk-circle"></div>' +
        '<div class="sk-circle11 sk-circle"></div>' +
        '<div class="sk-circle12 sk-circle"></div>' +
      '</div>';
});
