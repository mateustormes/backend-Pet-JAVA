package com.tormes.projeto.Projeto.Spring.AlbumFotos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumFotosRepository extends JpaRepository<AlbumFotos, Integer> {
    Optional<AlbumFotos> findPorteFisicoById(@Param("id") Integer id);

    List<AlbumFotos> findByDescricaoContaining(@Param("descricao") String descricao);
}