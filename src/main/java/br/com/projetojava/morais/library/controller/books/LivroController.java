package br.com.projetojava.morais.library.controller.books;

import br.com.projetojava.morais.library.model.books.Livro;
import br.com.projetojava.morais.library.service.books.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/livros"})
public class LivroController {


    @Autowired
    private LivroService livroService;


    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Livro> findAll(){
        return livroService.findAll();
    }

    @GetMapping(value = {"/{id}"})
    public ResponseEntity<Livro> findById(@PathVariable Long id){
        Livro livro = livroService.findById(id);

        return ResponseEntity.ok(livro);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Livro> create(@RequestBody Livro livro){

        if( livro != null){
            return ResponseEntity.ok(livroService.create(livro));
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping(value = {"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Livro> update(@PathVariable Long id, @RequestBody Livro livro){

        if (livro != null && id > 0){

            Livro newLivro = livroService.update(livro);

            return ResponseEntity.ok(newLivro);
            }

        return ResponseEntity.badRequest().build();

    }

    @DeleteMapping(value = {"/{id}"})
    public ResponseEntity<Void> delete(@PathVariable Long id){

        livroService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}


