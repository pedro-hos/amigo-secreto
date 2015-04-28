angular.module('amigo-secreto')
    .controller('UsuarioController', function ($scope, $location, $rootScope, $routeParams, usuarioService) {

        $scope.hide = "hide";

        if ($routeParams.id) {
            console.log('Vamos editar o usuario: ' + $routeParams.id);
            usuarioService.get({params: $routeParams.id},
                function (usuario) {
                    $scope.usuario = usuario;
                },

                function (erro) {
                    console.log(erro);

                    if (erro.status == 404) {
                        $scope.hide = "";
                        $scope.mensagem = {texto: "Usuário não encontrado!"};
                    }

                }
            );
        } else {
            console.log('Cadastrando novo');
            $scope.usuario = new usuarioService();
        }

        $scope.salvar = function () {
            $scope.usuario.id > 0 ? $scope.atualizar() : $scope.novo();
        };

        $scope.novo = function () {
            console.log($scope.usuario);

            $scope.usuario.$save()
                .then(function () {

                    $scope.usuario = new usuarioService();
                    $rootScope.$broadcast('contatoSalvo');
                    $location.path("#/");

                }).catch(function (erro) {

                    console.log(erro);

                    if (erro.status == 409) {
                        $scope.hide = "";
                        $scope.mensagem = {texto: "Usuário já cadastrado."};
                    }
                });
        };

        $scope.atualizar = function () {

            $scope.usuario.$update(
                {params: $scope.usuario.id},

                function () {
                    $scope.usuario = new usuarioService();
                    $rootScope.$broadcast('contatoEditado');
                    $location.path("#/");
                },

                function (erro) {

                    $scope.hide = "";

                    if (erro.status == 409) {
                        $scope.mensagem = {texto: "Usuário já cadastrado."};
                    } else if (erro.status == 400) {
                        $scope.mensagem = {texto: "Usuários para edição não conferem"};
                    } else if (erro.status == 404) {
                        $scope.mensagem = {texto: "Usuário não encontrado."};
                    } else {
                        $scope.mensagem = {texto: "Tenho vergonha de dizer isso, mas acho que é um erro 500 =("};
                    }

                }
            );
        }

    });