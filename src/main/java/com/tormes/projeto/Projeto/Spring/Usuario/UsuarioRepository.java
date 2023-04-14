package com.tormes.projeto.Projeto.Spring.Usuario;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findUsuarioById(@Param("id") Integer id);


    Optional<Usuario> findUsuarioByEmailAndSenha(@Param("email") String email, @Param("senha") String senha);
    List<Usuario> findByNomeContaining(@Param("nome") String nome);
}