package br.com.projetojava.morais.library.controller.books;


import br.com.projetojava.morais.library.model.books.Autor;
import br.com.projetojava.morais.library.service.books.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/autores"})
public class AutorController {

    @Autowired
    private AutorService autorService;

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Autor> findAll(){
        return autorService.findAll();
    }


    @GetMapping(value = {"/{id}"})
    public ResponseEntity<Autor> findById(@PathVariable Long id){
        Autor autor = autorService.findById(id);

        return ResponseEntity.ok(autor);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Autor> create(@RequestBody Autor autor){

        if( autor != null){
            return ResponseEntity.ok(autorService.create(autor));
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping(value = {"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Autor> update(@PathVariable Long id, @RequestBody Autor autor){

        if (autor != null && id > 0){

            Autor newAutor = autorService.update(autor);

            return ResponseEntity.ok(newAutor);
        }

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping(value = {"/{id}"})
    public ResponseEntity<Void> delete(@PathVariable Long id){

        autorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
