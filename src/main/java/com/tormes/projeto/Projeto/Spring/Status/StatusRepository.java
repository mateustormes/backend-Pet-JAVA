package com.tormes.projeto.Projeto.Spring.Status;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer> {
    Optional<Status> findStatusById(@Param("id") Integer id);

    List<Status> findByDescricaoContaining(@Param("descricao") String descricao);
}