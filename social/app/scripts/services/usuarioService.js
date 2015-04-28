angular.module('amigo-secreto')
    .factory('usuarioService', ['$resource', function ($resource) {

        return $resource($SERVICES_CONTEXT + 'usuario/:params', {}, {
            update: {
                method: "PUT"
            }
        });

    }]);