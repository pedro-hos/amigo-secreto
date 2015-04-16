package br.com.amigo.secreto.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.amigo.secreto.model.entities.usuario.Usuario;
import br.com.amigo.secreto.model.service.usuario.UsuarioService;

@RestController
@RequestMapping(value="api/usuario")
public class UsuarioController {
	
	@Autowired UsuarioService usuarioService;
	
	@RequestMapping( value = {"/", ""}, 
					 method = GET,
					 produces = APPLICATION_JSON_VALUE )
	public ResponseEntity<List<Usuario>> getAll() {
		
		return new ResponseEntity<List<Usuario>>( usuarioService.findAll(), 
												  HttpStatus.OK );
	}
}
