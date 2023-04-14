package com.tormes.projeto.Projeto.Spring.CategoriaUsuario;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaUsuarioRepository extends JpaRepository<CategoriaUsuario, Integer> {
    Optional<CategoriaUsuario> findCategoriaUsuarioById(@Param("id") Integer id);

    List<CategoriaUsuario> findByDescricaoContaining(@Param("descricao") String descricao);
}