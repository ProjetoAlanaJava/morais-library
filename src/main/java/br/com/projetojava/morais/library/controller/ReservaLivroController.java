package br.com.projetojava.morais.library.controller;

import br.com.projetojava.morais.library.model.Emprestimo;
import br.com.projetojava.morais.library.model.ReservaLivro;
import br.com.projetojava.morais.library.service.ReservaLivroService;
import br.com.projetojava.morais.library.util.Constantes;
import br.com.projetojava.morais.library.util.Exceptions;
import br.com.projetojava.morais.library.util.GetData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping({"/reserva-livro"})
public class ReservaLivroController {

    private ReservaLivroService service;

    public ReservaLivroController(ReservaLivroService reservaLivroService) {
        this.service = reservaLivroService;
    }

    /* Achar todas as Reservas por id de Usuario */

    /**
     * @param id -> fazer a request com o id do usuario para retornar todos as reservas de livro que ele possui!!!!!
     *
     * @return 200, junto com a lista de todos as reservas com aquele id
     * 400 - Se o id for invalido
     * 402 - Se não achar nenhum registro com id
     */
    @GetMapping(path = {"/{id}"})
    public ResponseEntity<?> emprestimosPorUsuario(@PathVariable long id) {

        if(id > 0) {

            List<ReservaLivro> reservas = service.findByUsuario(id);
            if(Objects.nonNull(reservas)) {
                return ResponseEntity.ok().body(reservas);
            } else {
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.badRequest().build();
    }


    /**
     *
     * @param reserva seu modelo é:
     *{
     *     "livro": {"id": 1},
     *     "usuario" : {"id": 1}
     * }
     *  A data máxima para efeturar o empréstimo vai ser setada pelo proprio sistema!!!
     * @return 200 - Se salvar a reserva, junto com o data máxima para pegar o emprestimo;
     * 400 - Se algum parametro estiver fora do normal
     * Exception - Caso já existe alguma reserva com o livro especificado!
     */

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody ReservaLivro reserva) {

        if(Objects.nonNull(reserva)) {

            ReservaLivro reservaAchada = service.findByLivro(reserva.getLivro());
            Exceptions.checkAndThrow(Objects.nonNull(reservaAchada), Constantes.RESERVA_LIVRO_EXISE);

            GetData currentData = new GetData();
            reserva.setDataMaximaEmprestimo(currentData.getReservaData());
            service.save(reserva);
            return ResponseEntity.ok().body(reserva.getDataMaximaEmprestimo());

        }
        return ResponseEntity.badRequest().body("Campos vazios na request!");
    }

    /**
     *
     * @param id exemplo : http://localhost:8080/reserva-livro/1

     * @return
     * 200 - Se conseguir deletar a reserva com sucesso
     * 400 - Se não achar nenhuma reserva com o id informado
     * 404 - Se o id informado não for válido
     */
    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<Object> delete(@PathVariable long id) {

        if(id > 0) {
            return service.findById(id)
                    .map(reservation -> {
                        service.deleteById(id);
                        return ResponseEntity.ok().build();
                    }).orElse(ResponseEntity.notFound().build());
        }

        return ResponseEntity.badRequest().build();
    }
}
