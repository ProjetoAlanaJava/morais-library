package br.com.projetojava.morais.library.controller;

import br.com.projetojava.morais.library.model.Evento;
import br.com.projetojava.morais.library.service.EventoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping({"/eventos"})
public class EventoController {

    private final EventoService service;

    public EventoController(EventoService eventoService) {
        service = eventoService;
    }

    @GetMapping
    public List<Evento> acharTodos() {
        return service.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<Evento> acharPorId(@PathVariable long id){

        if(id > 0) {
            return service.findById(id)
                    .map(event -> ResponseEntity.ok().body(event))
                    .orElse(ResponseEntity.notFound().build());
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping
    public ResponseEntity<Evento> salvar(@RequestBody Evento evento) {

        if(Objects.nonNull(evento)) {
            return ResponseEntity.ok().body(service.save(evento));
        }

        return ResponseEntity.badRequest().build();
    }

    @PutMapping(path = {"/{id}"})
    public ResponseEntity<Evento> atualizar(@PathVariable("id") long id, @RequestBody Evento evento) {

        if(id > 0 && Objects.nonNull(evento)){

            return service.findById(id)
                    .map(event -> {
                        event.setTitle(evento.getTitle());
                        event.setDate(evento.getDate());
                        event.setHorarioInicio(evento.getHorarioInicio());
                        event.setHorarioTermino(evento.getHorarioTermino());
                        event.setStatus(evento.getStatus());
                        return ResponseEntity.ok().body(service.save(event));
                    }).orElse(ResponseEntity.notFound().build());

        }

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<Object> delete(@PathVariable long id){

        if(id > 0) {
            return service.findById(id)
                    .map(user -> {
                        service.deleteById(id);
                        return ResponseEntity.ok().build();
                    }).orElse(ResponseEntity.notFound().build());

        }

        return ResponseEntity.badRequest().build();

    }

}
