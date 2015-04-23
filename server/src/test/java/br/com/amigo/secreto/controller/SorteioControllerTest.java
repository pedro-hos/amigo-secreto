package br.com.amigo.secreto.controller;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.com.amigo.secreto.config.TestContext;
import br.com.amigo.secreto.config.WebConfig;
import br.com.amigo.secreto.model.entities.usuario.Usuario;
import br.com.amigo.secreto.model.service.usuario.Usuarios;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestContext.class, WebConfig.class})
@WebAppConfiguration
public class SorteioControllerTest {
	
	public static final String CONTEXT = "http://localhost:8080/";
	
	private MockMvc mockMvc;
	
	@Autowired
	private Usuarios usuarios;
	
	@Autowired
    private WebApplicationContext webApplicationContext;
	
	private Usuario a = null;
	private Usuario b = null;
	private Usuario c = null;
	
	@Before
	public void setUp() {
		 Mockito.reset(usuarios);
		 mockMvc = MockMvcBuilders.standaloneSetup( new SorteioController(usuarios) )
				 				  .build();
		 
		 a = Usuario.novo()
				 	.nome("a")
				 	.email("a@a.com")
				 	.build();
		 
		 b = Usuario.novo()
				 	.nome("b")
				 	.email("b@b.com")
				 	.build();
		 
		 c = Usuario.novo()
				 	.nome("c")
				 	.email("c@c.com")
				 	.build();
	}
	
	@Test
	public void deveRetornarOkParaSorteio() throws Exception {
		
		when(usuarios.todos())
			.thenReturn(Arrays.asList(a, b, c));
		
		when(usuarios.salvarTodos( anyCollectionOf(Usuario.class ) ))
			.thenReturn(Arrays.asList(a, b, c));
		
		 mockMvc.perform(get(CONTEXT + "api/sorteio"))
		 		.andExpect(status().isOk())
		 		.andExpect(jsonPath("$", hasSize(3)))
		 		.andExpect(jsonPath("$[0].nome", not(is("$[0].nome"))))
		 		.andExpect(jsonPath("$[1].nome", not(is("$[1].nome"))))
		 		.andExpect(jsonPath("$[2].nome", not(is("$[2].nome"))));
		 
		 verify(usuarios, times(1)).todos();
		 verify(usuarios, times(1)).salvarTodos( anyCollectionOf(Usuario.class ) );
		 
	     verifyNoMoreInteractions(usuarios);
	}
	
	@Test
	public void deveRetornarBadRequestParaSorteio() throws Exception {
		
		when(usuarios.todos())
			.thenReturn(Arrays.asList(a));
		
		 mockMvc.perform(get(CONTEXT + "api/sorteio"))
		 		.andExpect(status().isBadRequest());
		 
		 verify(usuarios, times(1)).todos();
		 verify(usuarios, times(0)).salvarTodos( anyCollectionOf(Usuario.class ) );
		 
	     verifyNoMoreInteractions(usuarios);
	}
	
	@Test
	public void deveRetornarNotFoundParaSorteio() throws Exception {
		
		when(usuarios.todos())
			.thenReturn(new ArrayList<Usuario>());
		
		 mockMvc.perform(get(CONTEXT + "api/sorteio"))
		 		.andExpect(status().isNotFound());
		 
		 verify(usuarios, times(1)).todos();
		 verify(usuarios, times(0)).salvarTodos( anyCollectionOf(Usuario.class ) );
		 
	     verifyNoMoreInteractions(usuarios);
	}
}
