package com.tormes.projeto.Projeto.Spring.PetUsuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class PetUsuarioController {
    @Autowired
    private PetUsuarioRepository petUsuarioRepository;
      
    @GetMapping("/petUsuarioAll")
    public List<PetUsuario> listAll() {
        List<PetUsuario> listFotosPet = petUsuarioRepository.findAll();
        return listFotosPet;
    }

    @GetMapping("/petUsuarioById/{id}")
    public ResponseEntity<PetUsuario> findPetUsuarioById(@PathVariable Integer id) {
        return petUsuarioRepository.findById(id)
           .map(record -> ResponseEntity.ok().body(record))
           .orElse(ResponseEntity.notFound().build());     
    }

    @PostMapping("/petUsuario")
    public PetUsuario cadastrarPetUsuario(@RequestBody PetUsuario petUsuario){
        LocalDate date = LocalDate.now();
        petUsuario.setDt_user(date);
        return petUsuarioRepository.save(petUsuario);
    }

    @PutMapping("/petUsuario/{id}")
    public ResponseEntity atualizarPetUsuario(@PathVariable Integer id, @RequestBody PetUsuario petUsuario){
        LocalDate date = LocalDate.now();
        petUsuario.setDt_user(date);

        return petUsuarioRepository.findById(id)
           .map(record -> {
               record.setPet(petUsuario.getPet());
               record.setUsuario(petUsuario.getUsuario());
               record.setCd_user(petUsuario.getCd_user());
               record.setDt_user(petUsuario.getDt_user());
               PetUsuario albumFotoss = petUsuarioRepository.save(record);
               return ResponseEntity.ok().body(albumFotoss);
           }).orElse(ResponseEntity.notFound().build());
    }

    
    @DeleteMapping("/petUsuario/{id}")
    public ResponseEntity<?> deletarPetUsuario(@PathVariable Integer id){
        return petUsuarioRepository.findById(id)
           .map(record -> {
               petUsuarioRepository.deleteById(id);
               return ResponseEntity.ok().build();
           }).orElse(ResponseEntity.notFound().build());
    }
}