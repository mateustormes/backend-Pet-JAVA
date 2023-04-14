package com.tormes.projeto.Projeto.Spring.Usuario;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;
      
    @GetMapping("/usuarioAll")
    public List<Usuario> listAll() {
        List<Usuario> listUsuarios = usuarioRepository.findAll();          
        return listUsuarios;
    }

    @GetMapping("/usuarioById/{id}")
    public ResponseEntity<Usuario> findUsuarioById(@PathVariable Integer id) {   
        return usuarioRepository.findById(id)
           .map(record -> ResponseEntity.ok().body(record))
           .orElse(ResponseEntity.notFound().build());     
    }

    @GetMapping("/usuarioLogin/{email}/{senha}")
    public ResponseEntity<Usuario> findUsuarioByEmailAndSenha(@PathVariable String email, @PathVariable String senha) {
        ResponseEntity<Usuario> usuarioResponseEntity = usuarioRepository.findUsuarioByEmailAndSenha(email, senha)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());

        return usuarioResponseEntity;
    }

    @GetMapping("/usuarioByNome/{nome}")
    public List<Usuario> findByNomeContaining(@PathVariable String nome) {
        List<Usuario> listUsuarios = usuarioRepository.findByNomeContaining(nome);          
        return listUsuarios;
    }

    @PostMapping("/usuario")
    public Usuario cadastrarUsuario(@RequestBody Usuario usuario){
        LocalDate date = LocalDate.now();
        usuario.setDt_user(date);

        return usuarioRepository.save(usuario);
    }

    @PutMapping("/usuario/{id}")
    public ResponseEntity atualizarUsuario(@PathVariable Integer id, @RequestBody Usuario usuario){
        LocalDate date = LocalDate.now();
        usuario.setDt_user(date);
        usuario.setId(id);
        return usuarioRepository.findById(id)
           .map(record -> {
               record.setId(usuario.getId());
               record.setNome(usuario.getNome());
               record.setCpf(usuario.getCpf());
               record.setEmail(usuario.getEmail());
               record.setSenha(usuario.getSenha());
               record.setFoto(usuario.getFoto());
               record.setFk_nivel_acesso(usuario.getFk_nivel_acesso());
               record.setCd_user(usuario.getCd_user());
               record.setDt_user(usuario.getDt_user());
               Usuario usuarios = usuarioRepository.save(record);
               return ResponseEntity.ok().body(usuarios);
           }).orElse(ResponseEntity.notFound().build());
    }

    
    @DeleteMapping("/usuario/{id}")
    public ResponseEntity<?> deletarUsuario(@PathVariable Integer id){
        return usuarioRepository.findById(id)
           .map(record -> {
            usuarioRepository.deleteById(id);
               return ResponseEntity.ok().build();
           }).orElse(ResponseEntity.notFound().build());
    }
}