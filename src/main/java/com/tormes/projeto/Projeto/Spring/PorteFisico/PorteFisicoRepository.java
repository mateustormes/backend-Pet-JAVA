package com.tormes.projeto.Projeto.Spring.PorteFisico;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PorteFisicoRepository extends JpaRepository<PorteFisico, Integer> {
    Optional<PorteFisico> findPorteFisicoById(@Param("id") Integer id);

    List<PorteFisico> findByDescricaoContaining(@Param("descricao") String descricao);
}