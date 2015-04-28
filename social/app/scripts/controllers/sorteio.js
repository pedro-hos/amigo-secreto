angular.module('amigo-secreto')
    .controller('SorteioController', function ($scope, $location, $rootScope, sorteioService, emailService) {

        $scope.usuarios = [];
        $scope.hide = "hide";

        function sortear() {
            sorteioService.query(
                function (usuarios) {
                    $scope.usuarios = usuarios;
                },

                function (erro) {

                    $scope.hide = "";

                    if (erro.status == 404) {
                        $scope.mensagem = {texto: "Nenhum usuario encontrado!", show: true, tipo: "warning"};
                    } else if (erro.status == 400) {
                        $scope.mensagem = {
                            texto: "Não tente me enganar. Para poder sortear tem que ter no mínimo 2 pessoas!",
                            tipo: "danger"
                        };
                    }
                });
        };

        sortear();

        $scope.email = new emailService();

        $scope.enviarEmail = function () {

            if (confirm('Você têm certeza que deseja enviar os emails?')) {

                $scope.email.$save()
                    .then(function () {
                        $rootScope.$broadcast('emailEnviado');
                        $location.path("#/");

                    }).catch(function (erro) {
                        console.log(erro);
                        $scope.hide = "";
                        $scope.mensagem = {texto: "Ops. temos um erro :("};
                    });

            }
        }

    });