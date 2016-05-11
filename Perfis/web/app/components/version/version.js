'use strict';

angular.module('autodomun.version', [
  'autodomun.version.interpolate-filter',
  'autodomun.version.version-directive'
])

.value('version', '0.1');
