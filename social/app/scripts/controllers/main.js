angular.module('amigo-secreto')
	.controller('MainController', function ($scope, usuarioService) {

		$scope.usuarios = [];
		$scope.mensagem = {texto: ''};

		function buscaUsuarios() {
			usuarioService.query(
				
				function(usuarios){
					$scope.usuarios = usuarios;
				},

				function(erro){ 
					if(erro.status == 404)
					$scope.mensagem = {texto: 'Nenhum usuario encontrado!'};	
				});
		};

		buscaUsuarios();

	});