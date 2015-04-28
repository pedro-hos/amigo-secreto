angular.module('amigo-secreto')
    .controller('MainController', function ($scope, $location, $rootScope, usuarioService) {

        $scope.usuarios = [];

        $rootScope.$on('contatoSalvo', function () {
            $rootScope.mensagem = {texto: "Usuário Cadastrado com Sucesso", show: true, tipo: "success"};
        });

        $rootScope.$on('contatoEditado', function () {
            $rootScope.mensagem = {texto: "Usuário Editado com Sucesso", show: true, tipo: "success"};
        });

        $rootScope.$on('emailEnviado', function () {
            $rootScope.mensagem = {texto: "Email Enviado com Sucesso", show: true, tipo: "success"};
        });

        function buscaUsuarios() {
            usuarioService.query(
                function (usuarios) {
                    $scope.usuarios = usuarios;
                },

                function (erro) {
                    if (erro.status == 404) {
                        $scope.mensagem = {texto: "Nenhum usuario encontrado!", show: true, tipo: "warning"};
                    }
                });
        };

        buscaUsuarios();

        $scope.remover = function (usuario) {

            if (confirm('Você têm certeza que deseja excluir?')) {
                usuarioService.delete(
                    {params: usuario.id},
                    buscaUsuarios,
                    function (erro) {
                        $rootScope.mensagem = {
                            texto: "Algo tem errado ao excluir!",
                            show: true,
                            tipo: "danger"
                        };
                    });
            }
        };

    });