package br.com.amigo.secreto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@Configuration
@EnableAutoConfiguration
@EnableSpringDataWebSupport
@ComponentScan
public class SpringBootRun {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SpringBootRun.class, args);
	}

}
