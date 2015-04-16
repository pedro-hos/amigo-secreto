package br.com.amigo.secreto.model.entities.usuario;

import javax.persistence.Column;
import javax.persistence.Entity;

import br.com.amigo.secreto.model.entities.DefaultEntity;

/**
 * 
 * @author pedro-hos
 * Classe de modelo para usuário com seus atributos
 *
 */

@Entity(name = "usuario")
public class Usuario extends DefaultEntity {
	
	private static final long serialVersionUID = 1L;
	
	public Usuario(String nome, String email) {
		super();
		this.nome = nome;
		this.email = email;
	}

	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false, unique = true)
	private String email;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
