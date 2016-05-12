'use strict';

describe('autodomun.version module', function() {
  beforeEach(module('autodomun.version'));

  describe('version service', function() {
    it('should return current version', inject(function(version) {
      expect(version).toEqual('0.1');
    }));
  });
});
