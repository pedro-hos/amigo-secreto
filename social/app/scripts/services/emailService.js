angular.module('amigo-secreto')
    .factory('emailService', ['$resource', function ($resource) {

        return $resource($SERVICES_CONTEXT + 'email/:params', {}, {/*codigão aqui*/});

    }]);