package br.com.projetojava.morais.library.controller;

import br.com.projetojava.morais.library.model.UserCredentials;
import br.com.projetojava.morais.library.service.CredentialsService;
import br.com.projetojava.morais.library.util.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping({"/auth"})
public class AuthController {

    @Autowired
    CredentialsService credentialsService;

    private final List<String> responseLogin = new ArrayList<>();

    @PostMapping(path = {"/signin"})
    public ResponseEntity<List<String>> login(@RequestBody UserCredentials userCredentials) {

        if(!Objects.isNull(userCredentials)) {
            return credentialsService.findByUserAndPass(userCredentials.getUserName(), userCredentials.getPassWord())
                    .map(user -> {
                        responseLogin.add(user.getUserName());
                        responseLogin.add(user.getAuthority());
                        return ResponseEntity.ok().body(responseLogin);
                    })
                    .orElse(ResponseEntity.notFound().build());
        }
        responseLogin.add(Constantes.USER_BAD_REQUEST);
        return ResponseEntity.badRequest().body(responseLogin);
    }

    @PostMapping(path = {"/signup"})
    public ResponseEntity<String> register(@RequestBody UserCredentials userCredentials) {

        UserCredentials user;

        if(!Objects.isNull(userCredentials)) {
            user = credentialsService.save(userCredentials);
            if(!Objects.isNull(user)) {
                return ResponseEntity.ok().body("Usuário criado com sucesso!");
            } else {
                return ResponseEntity.badRequest().body("Erro ao criar o usuário!");
            }
        }

        return ResponseEntity.badRequest().body(Constantes.USER_BAD_REQUEST);
    }
}
