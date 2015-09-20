'use strict';

describe('Controller: personsFormController', function () {

  // load the controller's module
  beforeEach(module('persons'));

  var personsFormController,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    personsFormController = $controller('personsFormController', {
      $scope: scope
    });
  }));

  it('should be properly instantiated', function () {
    expect(personsFormController).not.toBe(null);
  });
});