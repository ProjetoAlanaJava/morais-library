package br.com.projetojava.morais.library.controller.books;

import br.com.projetojava.morais.library.model.books.Categoria;
import br.com.projetojava.morais.library.service.books.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/categorias"})
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Categoria> findAll(){
        return categoriaService.findAll();
    }

    @GetMapping(value = {"/{id}"})
    public ResponseEntity<Categoria> findById(@PathVariable Long id){
        Categoria categoria = categoriaService.findById(id);

        return ResponseEntity.ok(categoria);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Categoria> create(@RequestBody Categoria categoria){

        if( categoria != null){
            return ResponseEntity.ok(categoriaService.create(categoria));
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping(value = {"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Categoria> update(@PathVariable Long id, @RequestBody Categoria categoria){

        if (categoria != null && id > 0){

            Categoria newCategoria = categoriaService.update(categoria);

            return ResponseEntity.ok(newCategoria);
        }

        return ResponseEntity.badRequest().build();

    }

    @DeleteMapping(value = {"/{id}"})
    public ResponseEntity<Void> delete(@PathVariable Long id){

        categoriaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
