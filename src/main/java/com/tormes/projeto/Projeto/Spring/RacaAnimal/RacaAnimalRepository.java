package com.tormes.projeto.Projeto.Spring.RacaAnimal;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RacaAnimalRepository extends JpaRepository<RacaAnimal, Integer> {
    Optional<RacaAnimal> findRacaAnimalById(@Param("id") Integer id);

    List<RacaAnimal> findByDescricaoContaining(@Param("descricao") String descricao);
}