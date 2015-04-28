angular.module('amigo-secreto')
    .factory('sorteioService', ['$resource', function ($resource) {

        return $resource($SERVICES_CONTEXT + 'sorteio/:params', {}, {/*codigão aqui*/});

    }]);