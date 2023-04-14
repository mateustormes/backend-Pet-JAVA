package com.tormes.projeto.Projeto.Spring.CategoriaAnimal;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaAnimalRepository extends JpaRepository<CategoriaAnimal, Integer> {
    Optional<CategoriaAnimal> findCategoriaAnimalById(@Param("id") Integer id);

    List<CategoriaAnimal> findByDescricaoContaining(@Param("descricao") String descricao);
}