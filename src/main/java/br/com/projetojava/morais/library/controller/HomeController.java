package br.com.projetojava.morais.library.controller;

import br.com.projetojava.morais.library.util.Constantes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/"})
public class HomeController {

    @GetMapping
    public ResponseEntity<String> find() {

        return ResponseEntity.ok().body(Constantes.REQUEST_DEFAULT);
    }

    @PostMapping
    public ResponseEntity<String> create() {


        return ResponseEntity.ok().body(Constantes.REQUEST_DEFAULT);
    }

    @PutMapping
    public ResponseEntity<String> update() {

        return ResponseEntity.ok().body(Constantes.REQUEST_DEFAULT);
    }

    @DeleteMapping
    public ResponseEntity<String> delete() {

        return ResponseEntity.ok().body(Constantes.REQUEST_DEFAULT);
    }



}
