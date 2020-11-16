package br.com.projetojava.morais.library.controller;

import br.com.projetojava.morais.library.model.FichaCatalografica;
import br.com.projetojava.morais.library.model.books.Autor;
import br.com.projetojava.morais.library.service.FichaCatalograficaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping(path = {"/ficha-catalografica"})
public class FichaCatalograficaController {

    FichaCatalograficaService service;

    public FichaCatalograficaController(FichaCatalograficaService fichaCatalograficaService) {
        service = fichaCatalograficaService;
    }

       /*
        Exemplo de Request /titulo
        Json com o nome do titulo:
        "titulo" :  "Desenvolvimento ambiental do pantanal Brasileiro"
     */
    @GetMapping(path = "/titulo")
    public ResponseEntity<FichaCatalografica> acharPorTitulo(@RequestBody FichaCatalografica ficha){

        if(Objects.nonNull(ficha)) {

            String titulo = ficha.getTitulo();

            return service.findByTitle(titulo).map(f ->
                    ResponseEntity.ok().body(f))
                    .orElse(ResponseEntity.notFound().build());
        }

        return ResponseEntity.badRequest().build();
    }

    /*
        Exemplo de Request /autor
        Json com o nome do autor:
        "name" : "Gabriel Moreira de Oliveira"
     */

    @GetMapping(path = "/autor")
    public ResponseEntity<FichaCatalografica> acharPorAutor(@RequestBody Autor autor) {

        if(Objects.nonNull(autor)) {

            String nomeAutor = autor.getName();

            return service.findByAuthor(nomeAutor).map(author ->
                    ResponseEntity.ok().body(author))
                    .orElse(ResponseEntity.notFound().build());
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping
    public FichaCatalografica adicionar(@RequestBody FichaCatalografica ficha) {

        if(Objects.nonNull(ficha)) {
            return service.save(ficha);
        }

        return null;
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable long id) {

        if(id > 0) {
            return service.findById(id)
                    .map(ficha -> {
                        service.deleteById(id);
                        return ResponseEntity.ok().build();
                    }).orElse(ResponseEntity.notFound().build());
        }

        return ResponseEntity.badRequest().build();
    }
}


