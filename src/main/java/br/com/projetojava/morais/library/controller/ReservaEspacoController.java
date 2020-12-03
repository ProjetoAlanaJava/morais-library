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

        List<ReservaEspaco> response = service.findAll();
        response.forEach(res -> {
            res.getUsuario().setPassword(null);
        });

        return response;
    }

    @GetMapping(path = {"usuario/{id}"})
    public ResponseEntity<?> acharPorIdUsuario(@PathVariable long id) {

        if(id > 0) {

            List<ReservaEspaco> response = service.findByUsuario(id);
            response.forEach(res -> {
                res.getUsuario().setPassword(null);
            });

            return ResponseEntity.ok().body(response);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<ReservaEspaco> acharPorId(@PathVariable long id){

        if(id > 0) {
            return service.findById(id)
                    .map(reservation -> {
                        reservation.getUsuario().setPassword(null);
                        return ResponseEntity.ok().body(reservation);
                    })
                    .orElse(ResponseEntity.notFound().build());
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody ReservaEspaco reserva) {

        if(Objects.nonNull(reserva)) {

            ReservaEspaco reservaAchada = service.findIfExist(reserva.getData(), reserva.getHorarioInicioReserva(), reserva.getHorarioFimReserva());

            if(Objects.nonNull(reservaAchada)) {
                return ResponseEntity.badRequest().body("Já existe uma reserva nesse horário");
            } else {
                return ResponseEntity.ok().body(service.save(reserva));
            }
        }

        return ResponseEntity.badRequest().body("Campos vazios na request!");
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
