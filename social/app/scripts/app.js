angular
    .module('amigo-secreto', ['ngResource', 'ngRoute'])
    .config(function ($routeProvider) {
        $routeProvider

            .when('/', {
                templateUrl: 'views/main.html',
                controller: 'MainController'
            })

            .when('/usuario', {
                templateUrl: 'views/novoUsuario.html',
                controller: 'UsuarioController'
            })

            .when('/usuario/:id', {
                templateUrl: 'views/novoUsuario.html',
                controller: 'UsuarioController'
            })

            .when('/sorteio', {
                templateUrl: 'views/sorteio.html',
                controller: 'SorteioController'
            })

            .otherwise({
                redirectTo: '/'
            });

    });

var $SERVICES_CONTEXT = "http://localhost:8080/api/";