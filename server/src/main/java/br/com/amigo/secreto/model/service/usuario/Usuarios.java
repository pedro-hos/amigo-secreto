package br.com.amigo.secreto.model.service.usuario;

import java.util.Collection;
import java.util.List;

import br.com.amigo.secreto.model.entities.usuario.Usuario;

public interface Usuarios {
	List<Usuario> todos();
	Usuario novo(Usuario usuario);
	Usuario comID(Long id);
	Usuario comEmail(String email);
	Usuario findByAmigoSecretoId(Long id);
	void remove(Long id);
	void removeAll();
	List<Usuario> salvarTodos(Collection<Usuario> usuarios);
	
}
