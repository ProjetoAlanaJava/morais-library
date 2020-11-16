package br.com.projetojava.morais.library.controller.books;

import br.com.projetojava.morais.library.model.books.Tipo;
import br.com.projetojava.morais.library.service.books.TipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping({"livros/tipos"})
public class TipoController {

    @Autowired
    private TipoService tipoService;

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Tipo> findAll(){
        return tipoService.findAll();
    }

    @GetMapping(value = {"/{id}"})
    public ResponseEntity<Tipo> findById(@PathVariable Long id){
        Tipo tipo = tipoService.findById(id);

        return ResponseEntity.ok(tipo);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Tipo> create(@RequestBody Tipo tipo){

        if( tipo != null){
            return ResponseEntity.ok(tipoService.create(tipo));
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping(value = {"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Tipo> update(@PathVariable Long id, @RequestBody Tipo tipo){

        if (tipo!= null && id > 0){

            Tipo newTipo = tipoService.update(tipo);

            return ResponseEntity.ok(newTipo);
        }

        return ResponseEntity.badRequest().build();

    }

    @DeleteMapping(value = {"/{id}"})
    public ResponseEntity<Void> delete(@PathVariable Long id){

        tipoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
