package com.tormes.projeto.Projeto.Spring.RacaAnimal;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RacaAnimalController {
    @Autowired
    private RacaAnimalRepository racaAnimalRepository;
      
    @GetMapping("/racaAnimalAll")
    public List<RacaAnimal> listAll() {
        List<RacaAnimal> listRacaAnimal = racaAnimalRepository.findAll();          
        return listRacaAnimal;
    }

    @GetMapping("/racaAnimalById/{id}")
    public ResponseEntity<RacaAnimal> findRacaAnimalById(@PathVariable Integer id) {   
        return racaAnimalRepository.findById(id)
           .map(record -> ResponseEntity.ok().body(record))
           .orElse(ResponseEntity.notFound().build());     
    }

    @GetMapping("/racaAnimalByDescricao/{descricao}")
    public List<RacaAnimal> findByNomeContaining(@PathVariable String descricao) {
        List<RacaAnimal> listRacaAnimal = racaAnimalRepository.findByDescricaoContaining(descricao);          
        return listRacaAnimal;
    }

    @PostMapping("/racaAnimal")
    public RacaAnimal cadastrarRacaAnimal(@RequestBody RacaAnimal racaAnimal){
        LocalDate date = LocalDate.now();
        racaAnimal.setDt_user(date);
        return racaAnimalRepository.save(racaAnimal);
    }

    @PutMapping("/racaAnimal/{id}")
    public ResponseEntity atualizarRacaAnimal(@PathVariable Integer id, @RequestBody RacaAnimal racaAnimal){
        LocalDate date = LocalDate.now();
        racaAnimal.setDt_user(date);
        return racaAnimalRepository.findById(id)
           .map(record -> {
               record.setId(id);
               record.setDescricao(racaAnimal.getDescricao());
               record.setCd_user(racaAnimal.getCd_user());
               record.setDt_user(racaAnimal.getDt_user());
               RacaAnimal racaAnimals = racaAnimalRepository.save(record);
               return ResponseEntity.ok().body(racaAnimals);
           }).orElse(ResponseEntity.notFound().build());
    }

    
    @DeleteMapping("/racaAnimal/{id}")
    public ResponseEntity<?> deletarRacaAnimal(@PathVariable Integer id){
        return racaAnimalRepository.findById(id)
           .map(record -> {
            racaAnimalRepository.deleteById(id);
               return ResponseEntity.ok().build();
           }).orElse(ResponseEntity.notFound().build());
    }
}