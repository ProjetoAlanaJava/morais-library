package br.com.projetojava.morais.library.controller;

import br.com.projetojava.morais.library.model.Usuario;
import br.com.projetojava.morais.library.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping({"/usuarios"})
public class UsuarioController {

    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List findAll() {
        return usuarioService.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity findById(@PathVariable long id) {

        if(id > 0) {
            return usuarioService.findById(id)
                    .map(user -> ResponseEntity.ok().body(user))
                    .orElse(ResponseEntity.notFound().build());
        }

        return ResponseEntity.badRequest().build();

    }

    @PostMapping
    public Usuario create(@RequestBody Usuario usuario) {

        if(usuario != null) {
            return usuarioService.save(usuario);
        }

        return null;

    }

    @PutMapping(value = {"/{id}"})
    public ResponseEntity<Usuario> update(@PathVariable("id") long id, @RequestBody Usuario usuario){

        if(usuario != null && id > 0) {
            return usuarioService.findById(id)
                    .map(user -> {
                        user.setNome(usuario.getNome());
                        user.setCpf(usuario.getCpf());
                        user.setAtivo(usuario.getAtivo());
                        user.setCurso(usuario.getCurso());
                        user.setDepartamento(usuario.getDepartamento());
                        user.setCargo(usuario.getCargo());
                        user.setTipo(usuario.getTipo());
                        user.setLimiteLivros(usuario.getLimiteLivros());
                        user.setTelefone(usuario.getTelefone());
                        user.setEmail(usuario.getEmail());
                        Usuario atualizado = usuarioService.save(user);
                        return ResponseEntity.ok().body(atualizado);
                    }).orElse(ResponseEntity.notFound().build());
        }

       return ResponseEntity.badRequest().build();
    }

    @DeleteMapping(value = {"/{id}"})
    public ResponseEntity<Object> delete(@PathVariable long id){

        if(id > 0) {
            usuarioService.findById(id)
                    .map(user -> {
                        usuarioService.deleteById(id);
                        return ResponseEntity.ok().build();
                    }).orElse(ResponseEntity.notFound().build());

        }

        return ResponseEntity.badRequest().build();

    }


}
