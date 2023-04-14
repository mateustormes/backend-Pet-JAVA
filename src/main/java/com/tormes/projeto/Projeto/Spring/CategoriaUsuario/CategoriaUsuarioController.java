package com.tormes.projeto.Projeto.Spring.CategoriaUsuario;

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
public class CategoriaUsuarioController {
    @Autowired
    private CategoriaUsuarioRepository categoriaUsuarioRepository;
      
    @GetMapping("/categoriaUsuarioAll")
    public List<CategoriaUsuario> listAll() {
        List<CategoriaUsuario> listCategoriaUsuario = categoriaUsuarioRepository.findAll();          
        return listCategoriaUsuario;
    }

    @GetMapping("/categoriaUsuarioById/{id}")
    public ResponseEntity<CategoriaUsuario> findCategoriaUsuarioById(@PathVariable Integer id) {   
        return categoriaUsuarioRepository.findById(id)
           .map(record -> ResponseEntity.ok().body(record))
           .orElse(ResponseEntity.notFound().build());     
    }

    @GetMapping("/categoriaUsuarioByDescricao/{descricao}")
    public List<CategoriaUsuario> findByDescricaoContaining(@PathVariable String descricao) {
        List<CategoriaUsuario> listCategoriaUsuario = categoriaUsuarioRepository.findByDescricaoContaining(descricao);          
        return listCategoriaUsuario;
    }

    @PostMapping("/categoriaUsuario")
    public CategoriaUsuario cadastrarCategoriaUsuario(@RequestBody CategoriaUsuario categoriaUsuario){
        LocalDate date = LocalDate.now();
        categoriaUsuario.setDt_user(date);
        return categoriaUsuarioRepository.save(categoriaUsuario);
    }

    @PutMapping("/categoriaUsuario/{id}")
    public ResponseEntity atualizarCategoriaUsuario(@PathVariable Integer id, @RequestBody CategoriaUsuario categoriaUsuario){
        return categoriaUsuarioRepository.findById(id)
           .map(record -> {
               record.setDescricao(categoriaUsuario.getDescricao());
               record.setStatus(categoriaUsuario.getStatus());
               record.setCd_user(categoriaUsuario.getCd_user());
               record.setDt_user(categoriaUsuario.getDt_user());
               CategoriaUsuario categoriaUsuarios = categoriaUsuarioRepository.save(record);
               return ResponseEntity.ok().body(categoriaUsuarios);
           }).orElse(ResponseEntity.notFound().build());
    }

    
    @DeleteMapping("/categoriaUsuario/{id}")
    public ResponseEntity<?> deletarCategoriaUsuario(@PathVariable Integer id){
        return categoriaUsuarioRepository.findById(id)
           .map(record -> {
            categoriaUsuarioRepository.deleteById(id);
               return ResponseEntity.ok().build();
           }).orElse(ResponseEntity.notFound().build());
    }
}