angular
  .module('amigo-secreto', [ 'ngResource', 'ngRoute' ])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainController'
      })
      .otherwise({
        redirectTo: '/'
      });

  });

  var $SERVICES_CONTEXT = "";