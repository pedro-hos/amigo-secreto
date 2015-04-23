package br.com.amigo.secreto.model.entities.usuario;

public class UsuarioBuilder {

	public UsuarioBuilder() {
		this.usuario = new Usuario();
	}
	
	private Usuario usuario;
	
	public UsuarioBuilder email(String email) {
		usuario.setEmail(email);
		return this;
	}
	
	public UsuarioBuilder nome (String nome) {
		usuario.setNome(nome);
		return this;
	}
	
	public Usuario build() {
		return usuario;
	}
	
	

}
