package br.com.projetojava.morais.library.controller;

import br.com.projetojava.morais.library.model.UserCredentials;
import br.com.projetojava.morais.library.model.Usuario;
import br.com.projetojava.morais.library.service.UsuarioService;
import br.com.projetojava.morais.library.util.Constantes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping({"/auth"})
public class AuthController {

    private final UsuarioService service;

    public AuthController(UsuarioService usuarioService) {
        service = usuarioService;
    }

    @PostMapping(path = {"/signin"})
    public ResponseEntity<Usuario> login(@RequestBody UserCredentials userCredentials) {

        if(!Objects.isNull(userCredentials)) {

            return service.findByUserAndPass(userCredentials.getUsername(), userCredentials.getPassword())
                    .map(user -> ResponseEntity.ok().body(user))
                    .orElse(ResponseEntity.notFound().build());
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping(path = {"/signup"})
    public ResponseEntity<String> register(@RequestBody Usuario usuario) {

        Usuario user;

        if(!Objects.isNull(usuario)) {

            usuario.setAtivo(true);
            if(usuario.getAuthority().equals("aluno")) {
                usuario.setLimiteLivros(10);
            } else if(usuario.getAuthority().equals("professor")) {
                usuario.setLimiteLivros(15);
            } else {
                usuario.setLimiteLivros(5);
            }

            user = service.save(usuario);
            if(Objects.nonNull(user)) {
                return ResponseEntity.ok().body("Usuário criado com sucesso!");
            } else {
                return ResponseEntity.badRequest().body("Erro ao criar o usuário!");
            }
        }

        return ResponseEntity.badRequest().body(Constantes.USER_BAD_REQUEST);
    }

    @GetMapping(path = {"/usuarios"})
    public List<Usuario> findAll() {

        if(service.findAll().stream().iterator().hasNext()) {
            service.findAll().stream().iterator().next().setPassword(null);
        }

        return service.findAll();
    }

    @GetMapping(path = {"/usuarios/{id}"})
    public ResponseEntity<Usuario> findById(@PathVariable long id) {

        Usuario rUser = new Usuario();

        if(id > 0) {
            return service.findById(id)
                    .map(user -> {
                        rUser.setId(user.getId());
                        rUser.setMatricula(user.getMatricula());
                        rUser.setAuthority(user.getAuthority());
                        rUser.setNome(user.getNome());
                        rUser.setCpf(user.getCpf());
                        rUser.setAtivo(user.getAtivo());
                        rUser.setCurso(user.getCurso());
                        rUser.setCargo(user.getCargo());
                        rUser.setLimiteLivros(user.getLimiteLivros());
                        rUser.setTelefone(user.getTelefone());
                        rUser.setEmail(user.getEmail());

                        return ResponseEntity.ok().body(rUser);
                    })
                    .orElse(ResponseEntity.notFound().build());
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping(value = {"/update/{id}"})
    public ResponseEntity<Usuario> update(@PathVariable("id") long id, @RequestBody Usuario usuario){

        if(Objects.nonNull(usuario) && id > 0) {
            return service.findById(id)
                    .map(user -> {
                        user.setNome(usuario.getNome());
                        user.setAuthority(usuario.getAuthority());
                        user.setCpf(usuario.getCpf());
                        user.setAtivo(usuario.getAtivo());
                        user.setCurso(usuario.getCurso());
                        user.setCargo(usuario.getCargo());
                        user.setLimiteLivros(usuario.getLimiteLivros());
                        user.setTelefone(usuario.getTelefone());
                        user.setEmail(usuario.getEmail());
                        Usuario atualizado = service.save(user);
                        atualizado.setPassword(null);
                        return ResponseEntity.ok().body(atualizado);
                    }).orElse(ResponseEntity.notFound().build());
        }

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping(value = {"/delete/{id}"})
    public ResponseEntity<Object> delete(@PathVariable long id){

        if(id > 0) {
            return service.findById(id)
                    .map(user -> {
                        service.deleteById(id);
                        return ResponseEntity.ok().build();
                    }).orElse(ResponseEntity.notFound().build());

        }

        return ResponseEntity.badRequest().build();

    }


}
