package com.tormes.projeto.Projeto.Spring.PorteFisico;

import java.time.LocalDate;
import java.util.List;

import com.tormes.projeto.Projeto.Spring.Status.Status;
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
public class PorteFisicoController {
    @Autowired
    private PorteFisicoRepository porteFisicoRepository;
      
    @GetMapping("/porteFisicoAll")
    public List<PorteFisico> listAll() {
        List<PorteFisico> listPorteFisico = porteFisicoRepository.findAll();          
        return listPorteFisico;
    }

    @GetMapping("/porteFisicoById/{id}")
    public ResponseEntity<PorteFisico> findRacaAnimalById(@PathVariable Integer id) {   
        return porteFisicoRepository.findById(id)
           .map(record -> ResponseEntity.ok().body(record))
           .orElse(ResponseEntity.notFound().build());     
    }

    @GetMapping("/porteFisicoByDescricao/{descricao}")
    public List<PorteFisico> findByNomeContaining(@PathVariable String descricao) {
        List<PorteFisico> listPorteFisico = porteFisicoRepository.findByDescricaoContaining(descricao);          
        return listPorteFisico;
    }

    @PostMapping("/porteFisico")
    public PorteFisico cadastrarRacaAnimal(@RequestBody PorteFisico porteFisico){
        LocalDate date = LocalDate.now();
        porteFisico.setDt_user(date);
        Status status = new Status();
        status.setId(3);
        porteFisico.setFk_status(status);

        return porteFisicoRepository.save(porteFisico);
    }

    @PutMapping("/porteFisico/{id}")
    public ResponseEntity atualizarRacaAnimal(@PathVariable Integer id, @RequestBody PorteFisico porteFisico){

        return porteFisicoRepository.findById(id)
           .map(record -> {
               LocalDate date = LocalDate.now();
               porteFisico.setDt_user(date);

               Status status = new Status();
               status.setId(3);

               record.setDescricao(porteFisico.getDescricao());
               record.setCd_user(porteFisico.getCd_user());
               record.setDt_user(porteFisico.getDt_user());
               record.setFk_status(status);
               PorteFisico porteFisicos = porteFisicoRepository.save(record);
               return ResponseEntity.ok().body(porteFisicos);
           }).orElse(ResponseEntity.notFound().build());
    }

    
    @DeleteMapping("/porteFisico/{id}")
    public ResponseEntity<?> deletarRacaAnimal(@PathVariable String id){
        Integer idX = Integer.valueOf(id);
        return porteFisicoRepository.findById(idX)
           .map(record -> {
            porteFisicoRepository.deleteById(idX);
               return ResponseEntity.ok().build();
           }).orElse(ResponseEntity.notFound().build());
    }
}