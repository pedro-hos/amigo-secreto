package br.com.amigo.secreto.model.service.usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.amigo.secreto.model.entities.usuario.Usuario;
import br.com.amigo.secreto.model.repositories.usuario.Usuarios;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired private Usuarios usuarios;
	
	@Override
	public List<Usuario> findAll() {
		return (List<Usuario>) usuarios.findAll();
	}

	@Override
	public Usuario save(Usuario usuario) {
		return usuarios.save(usuario);
		
	}

}
