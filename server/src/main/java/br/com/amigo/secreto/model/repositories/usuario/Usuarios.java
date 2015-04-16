package br.com.amigo.secreto.model.repositories.usuario;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.amigo.secreto.model.entities.usuario.Usuario;

@Repository
public interface Usuarios extends CrudRepository<Usuario, Long> { }
