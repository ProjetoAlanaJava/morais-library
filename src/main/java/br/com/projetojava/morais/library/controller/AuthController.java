package br.com.projetojava.morais.library.controller;

import br.com.projetojava.morais.library.model.UserCredentials;
import br.com.projetojava.morais.library.model.Usuario;
import br.com.projetojava.morais.library.security.JWTAuthUtils;
import br.com.projetojava.morais.library.service.UsuarioService;
import br.com.projetojava.morais.library.util.Constantes;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping({"/auth"})
public class AuthController {

    private final UsuarioService service;
    private final JWTAuthUtils authentication;

    public AuthController(UsuarioService usuarioService, JWTAuthUtils auth) {
        service = usuarioService;
        authentication = auth;
    }

    @PostMapping(path = {"/signin"})
    public ResponseEntity<?> login(@RequestBody UserCredentials userCredentials) {

        if(!Objects.isNull(userCredentials)) {
            LinkedHashMap<String, Object> map = new LinkedHashMap<>();
            Usuario user = service.findByMatricula(userCredentials.getUsername());
            if(Objects.nonNull(user)) {
                boolean checkIsPass = passwordEncoder().matches(userCredentials.getPassword(), user.getPassword());
                if(checkIsPass) {
                    String tokenCreated = authentication.createToken(user.getNome());
                    user.setPassword(null);
                    map.put("token", tokenCreated);
                    map.put("usuario", user);
                    return ResponseEntity.ok().body(map);
                } else {
                    return ResponseEntity.badRequest().body("Senha incorreta!");
                }
            } else {
                return ResponseEntity.notFound().build();
            }
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

            String senhaCriptograda = passwordEncoder().encode(usuario.getPassword());
            usuario.setPassword(senhaCriptograda);
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

        List<Usuario> usersResponse = service.findAll();

        usersResponse.forEach(user -> {
            user.setPassword(null);
        });

        return usersResponse;
    }

    @GetMapping(path = {"/usuarios/{id}"})
    public ResponseEntity<Usuario> findById(@PathVariable long id) {

        if(id > 0) {
            return service.findById(id)
                    .map(user -> {
                        user.setPassword(null);
                        return ResponseEntity.ok().body(user);
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

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}
