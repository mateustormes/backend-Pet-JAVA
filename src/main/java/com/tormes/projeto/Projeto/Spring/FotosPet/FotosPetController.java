package com.tormes.projeto.Projeto.Spring.FotosPet;

import com.tormes.projeto.Projeto.Spring.Pet.Pet;
import com.tormes.projeto.Projeto.Spring.Pet.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
public class FotosPetController {
    @Autowired
    private FotosPetRepository fotosPetRepository;
    @Autowired
    private PetRepository petRepository;

    @GetMapping("/fotosPetAll")
    public List<FotosPet> listAll() {
        List<FotosPet> listFotosPet = fotosPetRepository.findAll();
        return listFotosPet;
    }

    @GetMapping("/fotosPetById/{id}")
    public ResponseEntity<FotosPet> findFotosPetById(@PathVariable Integer id) {
        return fotosPetRepository.findById(id)
           .map(record -> ResponseEntity.ok().body(record))
           .orElse(ResponseEntity.notFound().build());     
    }

    @PostMapping("/fotosPet")
    public FotosPet cadastrarFotosPet(@RequestBody FotosPet fotosPet){
        LocalDate date = LocalDate.now();
        fotosPet.setDt_user(date);
        if (fotosPet.getId() == null){
            Long id = fotosPetRepository.findMaxId();
            fotosPet.setId((int) (id+1));
        }
//        Optional<Pet> pet = petRepository.findById(fotosPet.getPet().getId());
//        fotosPet.setPet(new Pet());
//        fotosPet.getPet().setId(pet.get().getId());

        return fotosPetRepository.save(fotosPet);
    }

    @PutMapping("/fotosPet/{id}")
    public ResponseEntity atualizarFotosPet(@PathVariable Integer id, @RequestBody FotosPet fotosPet){
        LocalDate date = LocalDate.now();
        fotosPet.setDt_user(date);
        fotosPet.setId(id);


//        Optional<FotosPet> fotosPet1= fotosPetRepository.findById(id);
//        if (fotosPet1.get() != null){
//            fotosPet1.get().setId(fotosPet.getId());
//            fotosPet1.get().setFoto(fotosPet.getFoto());
//            fotosPet1.get().setPet(fotosPet.getPet());
//            fotosPet1.get().setCd_user(fotosPet.getCd_user());

            FotosPet albumFotoss = fotosPetRepository.save(fotosPet);
            return ResponseEntity.ok().body(albumFotoss);
//        }
//        return ResponseEntity.notFound().build();
    }

    
    @DeleteMapping("/fotosPet/{id}")
    public ResponseEntity<?> deletarFotosPet(@PathVariable Integer id){
        return fotosPetRepository.findById(id)
           .map(record -> {
               fotosPetRepository.deleteById(id);
               return ResponseEntity.ok().build();
           }).orElse(ResponseEntity.notFound().build());
    }
}