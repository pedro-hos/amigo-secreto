package br.com.amigo.secreto.model.service.usuario;

import java.util.List;

import br.com.amigo.secreto.model.entities.usuario.Usuario;

public interface Usuarios {
	List<Usuario> todos();
	Usuario novo(Usuario usuario);
	Usuario comID(Long id);
	Usuario comEmail(String email);
	void remove(Long id);
	void removeAll();
}
