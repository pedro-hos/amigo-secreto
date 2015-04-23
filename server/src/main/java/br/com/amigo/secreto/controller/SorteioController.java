package br.com.amigo.secreto.controller;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.amigo.secreto.model.entities.usuario.Usuario;
import br.com.amigo.secreto.model.service.usuario.Usuarios;

@RestController
@RequestMapping(value="api/sorteio")
public class SorteioController {
	
	private Usuarios usuarios;
	
	
	public SorteioController() {}

	@Autowired
	public SorteioController(Usuarios usuarios) {
		super();
		this.usuarios = usuarios;
	}

	@RequestMapping( method = GET, produces = APPLICATION_JSON_VALUE )
	public ResponseEntity<List<Usuario>> sortear() {
		
		List<Usuario> todos = usuarios.todos();
		
		if( todos.isEmpty() ) {
			return new ResponseEntity<List<Usuario>>(NOT_FOUND);
		} else if (todos.size() < 2) {
			return new ResponseEntity<List<Usuario>>(BAD_REQUEST);
		}
		
		sortearAmigos(todos);
		
		//Vamos armezenar quem tirou quem! \o
		usuarios.salvarTodos(todos);
		
		return new ResponseEntity<List<Usuario>>( todos, OK );
	}
	
	/**
	 * Método que realiza o sorteio, tomando cuidado para que a 
	 * pessoal não tire ela mesma e que não tirem ela mesma
	 */
	protected void sortearAmigos(List<Usuario> todos) {
		
		int indice = 0;
		List<Integer> jaSorteados = new ArrayList<Integer>();
		List<Usuario> auxiliar = new ArrayList<Usuario>(todos);
		
		for (Usuario usuario : todos) {
			
			/*
			 * Um cuidado extra para não dar caquinha por conta da recurvidade em
			 * retornaIndice(); Se ele for o ultimo basta pegar que esta sobrando
			 * na lista auxiliar.
			 */
			if(jaSorteados.size() == todos.size() - 1) {
				
				Usuario usuarioAuxiliar = auxiliar.get(0);
				
				if(usuario.equals(usuarioAuxiliar)) {
					Random random = new Random();
					
					//Pego todos e sorteio um, menos ele mesmo para trocar os amigos.
					Usuario usuarioSorteado = todos.get(random.nextInt(todos.size() - 1));
					
					usuario.setAmigoSecreto(usuarioSorteado.getAmigoSecreto());
					usuarioSorteado.setAmigoSecreto(usuarioAuxiliar);
	
					continue;
				}
				
				usuario.setAmigoSecreto(usuarioAuxiliar);
				continue;
				
			}
			
			indice = retornaIndice(todos.indexOf(usuario), todos.size(), jaSorteados);
			jaSorteados.add(indice);
			auxiliar.remove(todos.get(indice));
			
			usuario.setAmigoSecreto(todos.get(indice));
		}
		
	}
	
	/**
	 * Método que retira o índice para pegar na lista
	 */
	protected int retornaIndice( int indiceUsuarioNaLista, 
								 int maxParaSorteio, 
								 List<Integer> jaSorteados ) {
		
		Random random = new Random();
		int indice = random.nextInt(maxParaSorteio);
		
		if(indice == indiceUsuarioNaLista){
			return retornaIndice(indiceUsuarioNaLista, maxParaSorteio, jaSorteados);
		} else if (jaSorteados.contains(indice)) {
			return retornaIndice(indiceUsuarioNaLista, maxParaSorteio, jaSorteados);
		}
		
		return indice;
		
	}
	
}
