package br.com.amigo.secreto.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.amigo.secreto.model.entities.usuario.Usuario;
import br.com.amigo.secreto.model.service.usuario.Usuarios;

@RestController
@RequestMapping(value="api/email")
public class EmailController {
	
	private JavaMailSender javaMailSender;
	private Usuarios usuarios;
	
	/**
	 * @deprecated
	 */
	public EmailController( ) {}
	
	@Autowired
	EmailController( JavaMailSender javaMailSender,
					 Usuarios usuarios ) {
		
        this.javaMailSender = javaMailSender;
        this.usuarios = usuarios;
    }
	
	@RequestMapping(method = POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void  enviar() {
        
		for (Usuario usuario : usuarios.todos()) {
			
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(usuario.getEmail());
			mailMessage.setReplyTo("amigo-secreto@localhost");
			mailMessage.setFrom("amigo-secreto@localhost");
			
			String nomeAmigo = usuario.getAmigoSecreto().getNome();
			
			mailMessage.setSubject( "Seu amigo secreto é " + nomeAmigo );
			mailMessage.setText(  "É com imenso prazer que informamos que o seu amigo(a) secreto é o (a): " + nomeAmigo + ""
								+ ". Lembre de dar um presente bem bacana para ele(a)." );
			
			javaMailSender.send(mailMessage);
			
		}
		
        
    }

}
