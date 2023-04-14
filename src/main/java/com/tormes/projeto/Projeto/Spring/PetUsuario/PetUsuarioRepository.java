package com.tormes.projeto.Projeto.Spring.PetUsuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PetUsuarioRepository extends JpaRepository<PetUsuario, Integer> {
    Optional<PetUsuario> findPetUsuarioById(@Param("id") Integer id);

}