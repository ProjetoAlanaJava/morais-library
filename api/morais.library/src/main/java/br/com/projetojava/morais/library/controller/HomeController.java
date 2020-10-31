package br.com.projetojava.morais.library.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/"})
public class HomeController {

    private static final String REQUEST_DEFAULT =
            "Bem-vindo, essa é a MoraisLibrary-API! api de gestão de sistema de bibliotecário." +
                    "<br>Todos os path's expostos e os verbos HTTP que podem ser acessados podem ser encontrados em:  https://github.com/ProjetoAlanaJava/morais-library ";

    @GetMapping
    public ResponseEntity<String> find() {

        return ResponseEntity.ok().body(REQUEST_DEFAULT );
    }

    @PostMapping
    public ResponseEntity<String> create() {


        return ResponseEntity.ok().body(REQUEST_DEFAULT );
    }

    @PutMapping
    public ResponseEntity<String> update() {

        return ResponseEntity.ok().body(REQUEST_DEFAULT );
    }

    @DeleteMapping
    public ResponseEntity<String> delete() {

        return ResponseEntity.ok().body(REQUEST_DEFAULT );
    }
}
