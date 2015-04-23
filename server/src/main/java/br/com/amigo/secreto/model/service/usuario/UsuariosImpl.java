package br.com.amigo.secreto.model.service.usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.amigo.secreto.model.entities.usuario.Usuario;
import br.com.amigo.secreto.model.repositories.usuario.UsuarioRepository;

@Service(value="UsuariosImpl")
public class UsuariosImpl implements Usuarios {

	@Autowired private UsuarioRepository usuarioRepository;
	
	@Override
	public List<Usuario> todos() {
		return (List<Usuario>) usuarioRepository.findAll();
	}

	@Override
	public Usuario novo(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	@Override
	public Usuario comID(Long id) {
		return usuarioRepository.findOne(id);
	}

	@Override
	public Usuario comEmail(String email) {
		return usuarioRepository.findByEmailLike(email);
	}

	@Override
	public void remove(Long id) {
		usuarioRepository.delete(id);;
	}

	@Override
	public void removeAll() {
		usuarioRepository.deleteAll();
	}

	@Override
	public void salveAll(List<Usuario> usuarios) {
		usuarioRepository.save(usuarios);
	}

	@Override
	public Usuario findByAmigoSecretoId(Long id) {
		return usuarioRepository.findByAmigoSecretoId(id);
	}

}
