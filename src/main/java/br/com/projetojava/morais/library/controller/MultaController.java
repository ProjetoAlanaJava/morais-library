package br.com.projetojava.morais.library.controller;


import br.com.projetojava.morais.library.model.Multa;
import br.com.projetojava.morais.library.service.MultaService;
import br.com.projetojava.morais.library.util.Constantes;
import br.com.projetojava.morais.library.util.Exceptions;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping({"/multas"})
public class MultaController {

    MultaService service;

    public MultaController(MultaService multaService) {
        service = multaService;
    }

    /**
     * Se for necessário fazer a pesquisa da multa, esse método auxilia o processo.
     *
     * @param id do emprestimo a qual a multa está associada!
     * @return
     * 200 Multa achada
     * 402 Caso o id seja invalido
     * 404 Se não achar a multa
     */
    @GetMapping(path = {"/{id}"})
    public ResponseEntity<?> acharMulta(@PathVariable long id) {

        if(id > 0) {

            Multa multaAchada = service.findByEmprestimo(id);

            if(Objects.nonNull(multaAchada)) {
                return ResponseEntity.ok().body(multaAchada);
            }

            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.badRequest().build();
    }

    /**
     * Método para quando o usuario fizer o pagamento da multa, ser atualizado no sistema, para fazer a devoluão
     * sem problemas.
     * @param id da multa em questão que foi paga
     * @return
     * 200 - Se a multa foi atualizada com sucesso
     * 402 - Se o id não for válido
     */

    @PutMapping(path = {"/{id}"})
    public ResponseEntity<?> atualizarMulta(@PathVariable long id) {

        if(id > 0) {

            Multa multaAtualizar = service.findByid(id);
            Exceptions.checkAndThrow(Objects.isNull(multaAtualizar), Constantes.MULTA_NAO_ENCONTRADA);

            /*
                Atualiza de pendente para paga apenas, caso o valor esteja errado é necessário gerar uma nova multa,
                seguindo o fluxo de emprestimo, tentando realizar uma devolução!!!!!
             */

            multaAtualizar.setSituacao(Constantes.MULTA_OK);
            service.save(multaAtualizar);
            return ResponseEntity.ok().body(multaAtualizar);
        }

        return ResponseEntity.badRequest().build();

    }


    /**
     *  Caso seja necessário excluir a multa gerada ou haja uma duplicata, usar esse metodo, em outras ocasiões
     *  usar o fluxo de emprestimo, para que a multa seja deletada!!!!
     * @param id da multa a ser excluida
     * @return
     * 200 - Se deletar a multa
     * 402 - Se o id não for válido
     * 404 - Se não achar a multa a ser deletada
     * */
    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<?> deletarMulta(@PathVariable long id) {

        if(id > 0) {

            return service.findById(id)
                    .map(multa -> {
                        service.deleteById(id);
                        return ResponseEntity.ok().build();
                    }).orElse(ResponseEntity.notFound().build());
        }

        return ResponseEntity.badRequest().build();
    }
}
