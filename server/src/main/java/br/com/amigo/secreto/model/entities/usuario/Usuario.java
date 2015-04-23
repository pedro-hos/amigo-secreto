package br.com.amigo.secreto.model.entities.usuario;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import br.com.amigo.secreto.model.entities.DefaultEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author pedro-hos
 * Classe de modelo para usuário com seus atributos
 *
 */

@Entity(name = "usuario")
public class Usuario extends DefaultEntity {
	
	private static final long serialVersionUID = 1L;
	
	public Usuario() {}
	
	public Usuario(String nome, String email) {
		super();
		this.nome = nome;
		this.email = email;
	}

	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false, unique = true)
	private String email;
	
	@JsonIgnore
	@OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	private Usuario amigoSecreto;
	
	@Transient
	private String nomeAmigoSecreto;
	
	public static UsuarioBuilder novo() {
		return new UsuarioBuilder();
	}

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

	public Usuario getAmigoSecreto() {
		return amigoSecreto;
	}

	public void setAmigoSecreto(Usuario amigoSecreto) {
		this.amigoSecreto = amigoSecreto;
	}

	public String getNomeAmigoSecreto() {
		return nomeAmigoSecreto = amigoSecreto == null ? null : amigoSecreto.getNome();
	}

	public void setNomeAmigoSecreto(String nomeAmigoSecreto) {
		this.nomeAmigoSecreto = nomeAmigoSecreto;
	}

}
