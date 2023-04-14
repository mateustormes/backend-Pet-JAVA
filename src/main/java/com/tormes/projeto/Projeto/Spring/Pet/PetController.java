package com.tormes.projeto.Projeto.Spring.Pet;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.tormes.projeto.Projeto.Spring.RacaAnimal.RacaAnimal;
import com.tormes.projeto.Projeto.Spring.RacaAnimal.RacaAnimalRepository;
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
public class PetController {
    @Autowired
    private PetRepository petRepository;

    @Autowired
    private RacaAnimalRepository racaAnimalRepository;
      
    @GetMapping("/petAll")
    public List<Pet> listAll() {
        List<Pet> listPets = petRepository.findAll();          
        return listPets;
    }

    @GetMapping("/petById/{id}")
    public ResponseEntity<Pet> findCategoriaUsuarioById(@PathVariable Integer id) {   
        return petRepository.findById(id)
           .map(record -> ResponseEntity.ok().body(record))
           .orElse(ResponseEntity.notFound().build());     
    }

    @GetMapping("/petByNome/{nome}")
    public List<Pet> findByNomeContaining(@PathVariable String nome) {
        List<Pet> listPets = petRepository.findByNomeContaining(nome);          
        return listPets;
    }

    @PostMapping("/pet")
    public Pet cadastrarPet(@RequestBody Pet pet){
        LocalDate date = LocalDate.now();

        Optional<RacaAnimal> racaAnimal = racaAnimalRepository.findRacaAnimalById(Integer.valueOf(pet.getRacaAnimal().getId().toString()));
        pet.setRacaAnimal(racaAnimal.get());
        pet.setDt_user(date);
        return petRepository.save(pet);
    }

    @PutMapping("/pet/{id}")
    public ResponseEntity atualizarPets(@PathVariable Integer id, @RequestBody Pet pet){
        return petRepository.findById(id)
           .map(record -> {
               record.setNome(pet.getNome());
               record.setData_nascimento(pet.getData_nascimento());
               record.setRacaAnimal(pet.getRacaAnimal());
               record.setCd_user(pet.getCd_user());
               record.setDt_user(pet.getDt_user());
               Pet pets = petRepository.save(record);
               return ResponseEntity.ok().body(pets);
           }).orElse(ResponseEntity.notFound().build());
    }

    
    @DeleteMapping("/pet/{id}")
    public ResponseEntity<?> deletarPets(@PathVariable Integer id){
        return petRepository.findById(id)
           .map(record -> {
            petRepository.deleteById(id);
               return ResponseEntity.ok().build();
           }).orElse(ResponseEntity.notFound().build());
    }
}