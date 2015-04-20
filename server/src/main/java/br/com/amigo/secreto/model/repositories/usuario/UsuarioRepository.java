package br.com.amigo.secreto.model.repositories.usuario;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.amigo.secreto.model.entities.usuario.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
	Usuario findByEmailLike(String email);
	Usuario findByAmigoSecretoId(Long id);
}
