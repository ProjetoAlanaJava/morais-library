package br.com.projetojava.morais.library.controller.books;

import br.com.projetojava.morais.library.model.books.Editora;
import br.com.projetojava.morais.library.service.books.EditoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping({"/editoras"})
public class EditoraController {


    @Autowired
    private EditoraService editoraService;

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Editora> findAll(){
        return editoraService.findAll();
    }


    @GetMapping(value = {"/{id}"})
    public ResponseEntity<Editora> findById(@PathVariable Long id){
        Editora editora = editoraService.findById(id);

        return ResponseEntity.ok(editora);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Editora> create(@RequestBody Editora editora){

        if( editora != null){
            return ResponseEntity.ok(editoraService.create(editora));
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping(value = {"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Editora> update(@PathVariable Long id, @RequestBody Editora editora){

        if (editora != null && id > 0){

            Editora newEditora = editoraService.update(editora);

            return ResponseEntity.ok(newEditora);
        }

        return ResponseEntity.badRequest().build();

    }

    @DeleteMapping(value = {"/{id}"})
    public ResponseEntity<Void> delete(@PathVariable Long id){

        editoraService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
