package br.com.amigo.secreto.controller;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.amigo.secreto.model.entities.usuario.Usuario;
import br.com.amigo.secreto.model.service.usuario.Usuarios;

@RestController
@RequestMapping(value="api/usuario")
public class UsuarioController {
	
	@Autowired Usuarios usuarios;
	
	@RequestMapping( method = GET, produces = APPLICATION_JSON_VALUE )
	public ResponseEntity<List<Usuario>> getAll() {
		
		List<Usuario> todos = usuarios.todos();
		
		if( todos.isEmpty() ) {
			return new ResponseEntity<List<Usuario>>(NOT_FOUND);
		}
		
		return new ResponseEntity<List<Usuario>>( todos, OK );
	}
	
	@RequestMapping( value = "/{id}", method = GET, produces = APPLICATION_JSON_VALUE )
	public ResponseEntity<Usuario> getOne( @PathVariable("id") Long id ) {
	
		Usuario usuario = usuarios.comID(id);
		
		if( usuario == null ) {
			return new ResponseEntity<Usuario>(NOT_FOUND);
		}
		
		return new ResponseEntity<Usuario>( usuario, OK );
	}
	
	@RequestMapping( method = POST, 
					 consumes = APPLICATION_JSON_VALUE, 
					 produces = APPLICATION_JSON_VALUE )
	public ResponseEntity<Usuario> create( @RequestBody Usuario usuario ) {
		
		if(usuarios.comEmail(usuario.getEmail()) != null) {
			return new ResponseEntity<Usuario>(CONFLICT);
		}
		
		return new ResponseEntity<Usuario>(usuarios.novo(usuario), CREATED);
	}
	
	@RequestMapping( value = "/{id}", method = PUT,
			 		 consumes = APPLICATION_JSON_VALUE,
			 		 produces = APPLICATION_JSON_VALUE )
	public ResponseEntity<Usuario> update( @PathVariable("id") Long id,
										   @RequestBody Usuario usuario ) {
	
		if(!usuario.getId().equals(id)) {
			return new ResponseEntity<Usuario>(BAD_REQUEST);
		} else if(usuarios.comID(id) == null) {
			return new ResponseEntity<Usuario>(NOT_FOUND);
		} else if(usuarios.comEmail(usuario.getEmail()) != null) {
			return new ResponseEntity<Usuario>(CONFLICT);
		}
		
		return new ResponseEntity<Usuario>(usuarios.novo(usuario), OK);
	}
	
	@RequestMapping( value = "/{id}", method = DELETE, produces = APPLICATION_JSON_VALUE )
	public ResponseEntity<Usuario> delete( @PathVariable("id") Long id ) {
		
		if(usuarios.comID(id) == null) {
			return new ResponseEntity<Usuario>(NOT_FOUND);
		}
		
		usuarios.remove(id);
		return new ResponseEntity<Usuario>(OK);
	}
	
}