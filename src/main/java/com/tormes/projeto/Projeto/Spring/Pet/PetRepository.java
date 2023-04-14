package com.tormes.projeto.Projeto.Spring.Pet;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends JpaRepository<Pet, Integer> {
    Optional<Pet> findCategoriaUsuarioById(@Param("id") Integer id);

    List<Pet> findByNomeContaining(@Param("nome") String nome);
}