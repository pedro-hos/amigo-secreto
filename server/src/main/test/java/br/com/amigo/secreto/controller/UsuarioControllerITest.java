package br.com.amigo.secreto.controller;

import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.*;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.amigo.secreto.model.entities.usuario.Usuario;
import br.com.amigo.secreto.model.service.usuario.Usuarios;

/**
 * 
 * @author pedro-hos
 * 
 * Classe de teste de integração para os métodos REST do Usuário. 
 * Para rodar este teste necessário que o servidor esteja rodando,
 * para isso execute a classe: Application.java
 *
 */
public class UsuarioControllerITest {
	
	private static final String USUARIO_CONTEXT = "http://localhost:8080/api/usuario";
	
	@Autowired Usuarios usuarios;
	
	private Usuario usuario;
	private String usuarioJson;
	
	@Before
	public void setUp() throws Exception {
		
		usuarios.removeAll();
		
		usuario = new Usuario("Pedro Hos", "pedro-hos@outlook.com");
		
		ObjectMapper mapper = new ObjectMapper();
		usuarioJson = mapper.writeValueAsString(usuario);
	}
	
	@Test
	public void deveRetornar200AoBuscarTodos() {
/*		get(USUARIO_CONTEXT).
			then().assertThat().
				body(notNullValue()).
				body( "usuario.nome", contains("Pedro Hos") ).
				statusCode(200);
*/	}
	
	@Test
	public void deveRetornar404AoBuscarTodos() {
		/*get(USUARIO_CONTEXT).
			then().assertThat().
				body(nullValue()).
				statusCode(404);*/
	}
	
	@AfterClass
	public static void cleanBanco() {
		
	}

}
