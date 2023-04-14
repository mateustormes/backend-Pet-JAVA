package com.tormes.projeto.Projeto.Spring.AlbumFotos;

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
public class AlbumFotosController {
    @Autowired
    private AlbumFotosRepository albumFotosRepository;
      
    @GetMapping("/albumFotosAll")
    public List<AlbumFotos> listAll() {
        List<AlbumFotos> listAlbumFotos = albumFotosRepository.findAll();          
        return listAlbumFotos;
    }

    @GetMapping("/albumFotosById/{id}")
    public ResponseEntity<AlbumFotos> findAlbumFotosById(@PathVariable Integer id) {   
        return albumFotosRepository.findById(id)
           .map(record -> ResponseEntity.ok().body(record))
           .orElse(ResponseEntity.notFound().build());     
    }

    @GetMapping("/albumFotosByDescricao/{descricao}")
    public List<AlbumFotos> findByDescricaoContaining(@PathVariable String descricao) {
        List<AlbumFotos> listAlbumFotos = albumFotosRepository.findByDescricaoContaining(descricao);          
        return listAlbumFotos;
    }

    @PostMapping("/albumFotos")
    public AlbumFotos cadastrarAlbumFotos(@RequestBody AlbumFotos albumFotos){
        LocalDate date = LocalDate.now();
        albumFotos.setDt_user(date);
        return albumFotosRepository.save(albumFotos);
    }

    @PutMapping("/albumFotos/{id}")
    public ResponseEntity atualizarAlbumFotos(@PathVariable Integer id, @RequestBody AlbumFotos albumFotos){
        return albumFotosRepository.findById(id)
           .map(record -> {
               record.setDescricao(albumFotos.getDescricao());
               record.setPublico(albumFotos.getPublico());
               record.setCd_user(albumFotos.getCd_user());
               record.setDt_user(albumFotos.getDt_user());
               AlbumFotos albumFotoss = albumFotosRepository.save(record);
               return ResponseEntity.ok().body(albumFotoss);
           }).orElse(ResponseEntity.notFound().build());
    }

    
    @DeleteMapping("/albumFotos/{id}")
    public ResponseEntity<?> deletarAlbumFotos(@PathVariable Integer id){
        return albumFotosRepository.findById(id)
           .map(record -> {
            albumFotosRepository.deleteById(id);
               return ResponseEntity.ok().build();
           }).orElse(ResponseEntity.notFound().build());
    }
}