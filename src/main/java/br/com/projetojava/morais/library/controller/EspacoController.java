package br.com.projetojava.morais.library.controller;

import br.com.projetojava.morais.library.model.Espaco;
import br.com.projetojava.morais.library.service.EspacoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping({"/espacos"})
public class EspacoController {

    private final EspacoService espacoService;

    public EspacoController(EspacoService espaco) {
        espacoService = espaco;
    }

    @GetMapping
    public List<Espaco> acharTodos() {
        return espacoService.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<Espaco> acharPorId(@PathVariable long id) {

        if(id > 0) {
            return espacoService.findById(id)
                    .map(espaco -> ResponseEntity.ok().body(espaco))
                    .orElse(ResponseEntity.notFound().build());
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping
    public ResponseEntity<Espaco> salvar(@RequestBody Espaco espaco) {

        if(Objects.nonNull(espaco)) {
            return ResponseEntity.ok().body(espacoService.save(espaco));
        }

        return ResponseEntity.badRequest().build();
    }

    @PutMapping(path = {"/{id}"})
    public ResponseEntity<Espaco> atualizar(@PathVariable long id, @RequestBody Espaco espaco) {

        if(id > 0 && Objects.nonNull(espaco)) {
            return espacoService.findById(id)
                    .map(e -> {
                        e.setNome(espaco.getNome());
                        e.setSetor(espaco.getSetor());
                        e.setAndar(espaco.getAndar());
                        e.setHorarioAbertura(espaco.getHorarioAbertura());
                        e.setHorarioFechamento(espaco.getHorarioFechamento());
                        e.setTipo(espaco.getTipo());
                        e.setCapacidade(espaco.getCapacidade());
                        return ResponseEntity.ok().body(espacoService.save(e));
                    })
                    .orElse(ResponseEntity.notFound().build());
        }

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<Object> deletePorId(@PathVariable long id) {
        if(id > 0) {
            return espacoService.findById(id)
                    .map(espaco -> {
                        espacoService.deleteById(id);
                        return ResponseEntity.ok().build();
                    }).orElse(ResponseEntity.notFound().build());
        }

        return ResponseEntity.badRequest().build();
    }

}
