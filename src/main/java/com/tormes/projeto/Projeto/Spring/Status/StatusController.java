package com.tormes.projeto.Projeto.Spring.Status;

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
public class StatusController {
    @Autowired
    private StatusRepository statusRepository;
      
    @GetMapping("/statusAll")
    public List<Status> listAll() {
        List<Status> listStatus = statusRepository.findAll();          
        return listStatus;
    }

    @GetMapping("/statusById/{id}")
    public ResponseEntity<Status> findStatusById(@PathVariable Integer id) {   
        return statusRepository.findById(id)
           .map(record -> ResponseEntity.ok().body(record))
           .orElse(ResponseEntity.notFound().build());     
    }

    @GetMapping("/statusByDescricao/{descricao}")
    public List<Status> findByNomeContaining(@PathVariable String descricao) {
        List<Status> listStatus = statusRepository.findByDescricaoContaining(descricao);          
        return listStatus;
    }

    @PostMapping("/status")
    public Status cadastrarRacaAnimal(@RequestBody Status status){
        LocalDate date = LocalDate.now();
        status.setDt_user(date);

        return statusRepository.save(status);
    }

    @PutMapping("/status/{id}")
    public ResponseEntity atualizarRacaAnimal(@PathVariable Integer id, @RequestBody Status status){
        return statusRepository.findById(id)
           .map(record -> {
               record.setDescricao(status.getDescricao());
               record.setCd_user(status.getCd_user());
               record.setDt_user(status.getDt_user());
               Status statuss = statusRepository.save(record);
               return ResponseEntity.ok().body(statuss);
           }).orElse(ResponseEntity.notFound().build());
    }

    
    @DeleteMapping("/status/{id}")
    public ResponseEntity<?> deletarRacaAnimal(@PathVariable Integer id){
        return statusRepository.findById(id)
           .map(record -> {
            statusRepository.deleteById(id);
               return ResponseEntity.ok().build();
           }).orElse(ResponseEntity.notFound().build());
    }
}