package br.com.amigo.secreto.config;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.amigo.secreto.model.service.usuario.Usuarios;

@Configuration
public class TestContext {

	@Bean
	public Usuarios todoService() {
		return Mockito.mock(Usuarios.class);
	}

}
