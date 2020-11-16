package br.com.projetojava.morais.library.controller;

import br.com.projetojava.morais.library.model.ReservaEspaco;
import br.com.projetojava.morais.library.service.ReservaEspacoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(path = {"/reserva-espaco"})
public class ReservaEspacoController {

    private final ReservaEspacoService service;

    public ReservaEspacoController(ReservaEspacoService reservaEspacoService) {
        service = reservaEspacoService;
    }

    @GetMapping
    public List<ReservaEspaco> acharTodos() {
        return service.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<ReservaEspaco> acharPorId(@PathVariable long id){

        if(id > 0) {
            return service.findById(id)
                    .map(reservation -> ResponseEntity.ok().body(reservation))
                    .orElse(ResponseEntity.notFound().build());
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping
    public ResponseEntity<ReservaEspaco> salvar(@RequestBody ReservaEspaco reserva) {

        if(Objects.nonNull(reserva)) {
            return ResponseEntity.ok().body(service.save(reserva));
        }

        return ResponseEntity.badRequest().build();
    }

    @PutMapping(path = {"/{id}"})
    public ResponseEntity<ReservaEspaco> atualizar(@PathVariable("id") long id, @RequestBody ReservaEspaco reserva) {

        if(id > 0 && Objects.nonNull(reserva)){

            return service.findById(id)
                    .map(r -> {
                        r.setEvento(reserva.getEvento());
                        r.setEspaco(reserva.getEspaco());
                        r.setData(reserva.getData());
                        r.setHorarioInicioReserva(reserva.getHorarioInicioReserva());
                        r.setHorarioFimReserva(reserva.getHorarioInicioReserva());
                        return ResponseEntity.ok().body(service.save(r));
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
