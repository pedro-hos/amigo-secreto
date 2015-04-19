angular.module('amigo-secreto')
	.controller('UsuarioController', function ($scope, usuarioService) {

		$scope.usuario = new usuarioService();

		$scope.salvar = function() {

			console.log($scope.usuario);

			$scope.usuario.$save()
				.then(function(){
					$scope.mensagem = {texto: 'Salvo com sucesso'};
					$scope.usuario = new usuarioService();
				})
				.catch(function(erro) {

					console.log(erro);

					if(erro.status == 409)
						$scope.mensagem = {texto: 'Usuário já cadastrado.'};
				});
		};

	});