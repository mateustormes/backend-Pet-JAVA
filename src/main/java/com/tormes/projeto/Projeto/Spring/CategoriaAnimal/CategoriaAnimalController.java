package com.tormes.projeto.Projeto.Spring.CategoriaAnimal;

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
public class CategoriaAnimalController {
    @Autowired
    private CategoriaAnimalRepository categoriaAnimalRepository;
      
    @GetMapping("/categoriaAnimalAll")
    public List<CategoriaAnimal> listAll() {
        List<CategoriaAnimal> listCategoriaAnimals = categoriaAnimalRepository.findAll();          
        return listCategoriaAnimals;
    }

    @GetMapping("/categoriaAnimalById/{id}")
    public ResponseEntity<CategoriaAnimal> findCategoriaAnimalById(@PathVariable Integer id) {   
        return categoriaAnimalRepository.findById(id)
           .map(record -> ResponseEntity.ok().body(record))
           .orElse(ResponseEntity.notFound().build());     
    }

    @GetMapping("/categoriaAnimalByDescricao/{descricao}")
    public List<CategoriaAnimal> findByDescricaoContaining(@PathVariable String descricao) {
        List<CategoriaAnimal> listCategoriaAnimals = categoriaAnimalRepository.findByDescricaoContaining(descricao);          
        return listCategoriaAnimals;
    }

    @PostMapping("/categoriaAnimal")
    public CategoriaAnimal cadastrarCategoriaAnimal(@RequestBody CategoriaAnimal categoriaAnimal){
        LocalDate date = LocalDate.now();
        categoriaAnimal.setDt_user(date);
        return categoriaAnimalRepository.save(categoriaAnimal);
    }

    @PutMapping("/categoriaAnimal/{id}")
    public ResponseEntity atualizarCategoriaAnimal(@PathVariable Integer id, @RequestBody CategoriaAnimal categoriaAnimal){
        return categoriaAnimalRepository.findById(id)
           .map(record -> {
               record.setDescricao(categoriaAnimal.getDescricao());
               record.setFk_status(categoriaAnimal.getFk_status());
               record.setCd_user(categoriaAnimal.getCd_user());
               record.setDt_user(categoriaAnimal.getDt_user());
               CategoriaAnimal categoriaAnimals = categoriaAnimalRepository.save(record);
               return ResponseEntity.ok().body(categoriaAnimals);
           }).orElse(ResponseEntity.notFound().build());
    }

    
    @DeleteMapping("/categoriaAnimal/{id}")
    public ResponseEntity<?> deletarCategoriaAnimal(@PathVariable Integer id){
        return categoriaAnimalRepository.findById(id)
           .map(record -> {
            categoriaAnimalRepository.deleteById(id);
               return ResponseEntity.ok().build();
           }).orElse(ResponseEntity.notFound().build());
    }
}