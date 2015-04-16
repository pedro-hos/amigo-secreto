package br.com.amigo.secreto.model.service.usuario;

import java.util.List;

import br.com.amigo.secreto.model.entities.usuario.Usuario;

public interface UsuarioService {
	List<Usuario> findAll();
	Usuario save(Usuario usuario);
}
