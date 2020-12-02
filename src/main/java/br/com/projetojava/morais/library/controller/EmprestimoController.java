package br.com.projetojava.morais.library.controller;

import br.com.projetojava.morais.library.model.Emprestimo;
import br.com.projetojava.morais.library.model.Multa;
import br.com.projetojava.morais.library.model.ReservaLivro;
import br.com.projetojava.morais.library.model.Usuario;
import br.com.projetojava.morais.library.model.books.Livro;
import br.com.projetojava.morais.library.service.EmprestimoService;
import br.com.projetojava.morais.library.service.MultaService;
import br.com.projetojava.morais.library.service.ReservaLivroService;
import br.com.projetojava.morais.library.service.UsuarioService;
import br.com.projetojava.morais.library.service.books.LivroService;
import br.com.projetojava.morais.library.util.Constantes;
import br.com.projetojava.morais.library.util.Exceptions;
import br.com.projetojava.morais.library.util.GetData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(path = {"/emprestimo"})
public class EmprestimoController {

    EmprestimoService service;
    UsuarioService userSerivce;
    LivroService livroService;
    ReservaLivroService reservaLivroService;
    MultaService multaService;

    public EmprestimoController(EmprestimoService emprestimoService, UsuarioService usuarioService, LivroService livroServico, ReservaLivroService reservaLivroServico, MultaService multaServico) {
        service = emprestimoService;
        userSerivce = usuarioService;
        livroService = livroServico;
        reservaLivroService = reservaLivroServico;
        multaService = multaServico;
    }

    /**
     * @param id -> fazer a request com o id do usuario para retornar todos os emprestimos que ele possui!!!!!
     *
     * @return 200, junto com a lista de todos os emprestimos com aquele id
     * 400 - Se o id for invalido
     * 402 - Se não achar o id no banco!!!
    */
    @GetMapping(path = {"/{id}"})
    public ResponseEntity<List<Emprestimo>> emprestimosPorUsuario(@PathVariable long id) {

        if(id > 0) {

            List<Emprestimo> emps = service.findByUsuario(id);
            if(Objects.nonNull(emps)) {
                return ResponseEntity.ok().body(emps);
            } else {
                return ResponseEntity.notFound().build();
            }
        }

       return ResponseEntity.badRequest().build();
    }

    /**
     *
     * Exemplo de request de emprestimo, enviar o usuario com id e matricula e o funcionario com id e matricula,
     * livro necessita apenas do id!!!
     * Data, hora, data devolução e renvocações são todos setados no sistema na execução não se faz necessário enviar!!
     *
     * {
     *     "usuario": {"id": 1, "matricula": "20191022024"},
     *     "funcionario" : {"id":2 , "matricula": "20251025321"},
     *     "livro" : {"id": 1}
     * }
     *
     * @return 200 - Se emprestimo for feito com sucesso juntamente com a de devolução inicial
     * 404 ou Alguma Exception se não achar um  dos parametros passados como livro, funcionario ou usuario.
     * 400 - Se o objeto da request estiver vazio
    **/

    @PostMapping
    public ResponseEntity<?> realizarEmprestimo(@RequestBody Emprestimo emprestimo) {

        if(Objects.nonNull(emprestimo)) {

            Usuario userReq = userSerivce.findByMatricula(emprestimo.getUsuario().getMatricula());
            Exceptions.checkAndThrow(Objects.isNull(userReq), Constantes.USUARIO_NAO_ACHADO);

            Usuario funcReq = userSerivce.findByMatricula(emprestimo.getFuncionario().getMatricula());
            Exceptions.checkAndThrow(Objects.isNull(funcReq), Constantes.FUNCIONARIO_NAO_ACHADO);

            Livro livroReq = livroService.findById(emprestimo.getLivro().getId());
            ReservaLivro livroReservado = reservaLivroService.findByLivro(livroReq);
            boolean livroEstaReservado = Objects.nonNull(livroReservado); // verificando se o livro esta
            boolean situacaoLivroReserva = true;
            if(livroEstaReservado) { // evitando null pointer caso o livro não esteja reservado!!!!
                 situacaoLivroReserva = service.verificarValido(userReq.getMatricula(), livroReservado.getUsuario().getMatricula(),
                        livroReservado.getDataMaximaEmprestimo());
            }

            if(situacaoLivroReserva || livroEstaReservado) {

                GetData currentData = new GetData();
                emprestimo.setDataEmprestimo(currentData.getCurrentData());
                emprestimo.setHoraEmprestimo(currentData.getCurrentHour());

                if(livroEstaReservado){
                    reservaLivroService.deleteById(livroReservado.getId()); //deletando reserva feita para não gerar futuros conflitos
                }

                if(userReq.getAuthority().equals("aluno")) {
                    emprestimo.setDataDevolucao(currentData.getDevolucaoData(Constantes.TEMPO_DEVOLUCAO_ALUNO));
                    service.save(emprestimo);
                    return ResponseEntity.ok().body(emprestimo.getDataDevolucao());

                } else if(userReq.getAuthority().equals("professor")) {
                    emprestimo.setDataDevolucao(currentData.getDevolucaoData(Constantes.TEMPO_DEVOLUCAO_PROFESSOR));
                    service.save(emprestimo);
                    return ResponseEntity.ok().body(emprestimo.getDataDevolucao());

                } else if(userReq.getAuthority().equals("externo")) {
                    emprestimo.setDataDevolucao(currentData.getDevolucaoData(Constantes.TEMPO_DEVOLUCAO_EXTERNO));
                    service.save(emprestimo);
                    return ResponseEntity.ok().body(emprestimo.getDataDevolucao());
                }
            } else {
                return  ResponseEntity.badRequest().body("O livro já está reservado!");
            }
        }
        return ResponseEntity.badRequest().build();
    }

    /**
     * @param -> passando o id para renovar o emprestimo, tendo direito a 3 renovaões dentro do limite prazo para cada usuario
     * @!return 200 - se conseguir renovar o emprestimo, vai devolver nova data
     * 400 - Se já tiver renovado as tres vezes
     * 402 - Se o id nao for valido
     * */
    @PutMapping(value = {"/renovar/{id}"})
    public ResponseEntity<?> renovarEmprestimo(@PathVariable("id") long id){

        if(id > 0) {

            Emprestimo emprestimoAtualizar = service.findById(id);
            Exceptions.checkAndThrow(Objects.isNull(emprestimoAtualizar), Constantes.EMPRESTIMO_NAO_ENCONTRADO);
            GetData currentData = new GetData();

            if(emprestimoAtualizar.getRenovacoes() >= 3 ) {
                return ResponseEntity.badRequest().body("Já atingiu o máximo de renovações!");
            } else {

                String novaDev;
                if(emprestimoAtualizar.getUsuario().getAuthority().equals("aluno")) {
                    novaDev = currentData.novaDataDevolucao(emprestimoAtualizar.getDataDevolucao(), Constantes.TEMPO_DEVOLUCAO_ALUNO);
                    emprestimoAtualizar.setDataDevolucao(novaDev);
                } else if(emprestimoAtualizar.getUsuario().getAuthority().equals("professor")) {
                    novaDev = currentData.novaDataDevolucao(emprestimoAtualizar.getDataDevolucao(), Constantes.TEMPO_DEVOLUCAO_PROFESSOR);
                    emprestimoAtualizar.setDataDevolucao(novaDev);
                } else {
                    novaDev = currentData.novaDataDevolucao(emprestimoAtualizar.getDataDevolucao(), Constantes.TEMPO_DEVOLUCAO_EXTERNO);
                    emprestimoAtualizar.setDataDevolucao(novaDev);
                }

                emprestimoAtualizar.setRenovacoes(emprestimoAtualizar.getRenovacoes() + 1 );
                service.save(emprestimoAtualizar);
                return ResponseEntity.ok().body(emprestimoAtualizar.getDataDevolucao());
            }
        }
        return ResponseEntity.badRequest().build();
    }

    /**
     *
     * @param id -> ID do emprestimo a ser deletado!!
     * @return 200 - Se ele conseguir deletar emprestimo
     * 200 - Com a multa gerada pelo emprestimo, se for o caso
     * 200 - Se a multa não foi paga ainda, um aviso que a multa não foi paga
     * 402 - Se o id não for válido
     */
    @DeleteMapping(value = {"/{id}"})
    public ResponseEntity<?> realizarDevolucao(@PathVariable long id) {

        if(id > 0) {

            Emprestimo devolucao = service.findById(id);
            Exceptions.checkAndThrow(Objects.isNull(devolucao), Constantes.EMPRESTIMO_NAO_ENCONTRADO);
            boolean devolucaoAtrasada = service.verificarDataDevolucao(devolucao.getDataDevolucao());

            if(devolucaoAtrasada) {

                Multa acharMulta = multaService.findByEmprestimo(id);
                if(Objects.nonNull(acharMulta)) {

                    if(acharMulta.getSituacao().equals(Constantes.MULTA_OK)) {
                        multaService.deleteById(acharMulta.getId());
                        service.delete(id);
                        return ResponseEntity.ok().body("Devolução realizada com sucesso!");
                    }

                    return ResponseEntity.ok().body("Para realizar a devolução primeiro é preciso realizar o pagamento da multa!");
                } else {

                    Multa novaMulta = new Multa();
                    novaMulta.setEmprestimo(devolucao);
                    novaMulta.setSituacao(Constantes.MULTA_PENDENTE);
                    int diasAtrasados = multaService.calcularValorMulta(devolucao.getDataDevolucao());
                    double valorNaoFormatado;

                    /*  Valor por atraso para todos R$0,50!!!
                        Multa = DiasAtrasados * 0,50
                        Apenas professor recebe desconto na multa, que no caso é de 50%

                    */

                    if(devolucao.getUsuario().getAuthority().equals("professor")) {
                        valorNaoFormatado = (diasAtrasados * 0.50) / 2;
                        novaMulta.setValor(Double.toString(valorNaoFormatado));
                    } else {
                        valorNaoFormatado = diasAtrasados * 0.50;
                        novaMulta.setValor(Double.toString(valorNaoFormatado));
                    }

                    return ResponseEntity.ok().body("Multa a ser paga gerada no valor de R$" + novaMulta.getValor());
                }

            } else {
                service.delete(id);
                return ResponseEntity.ok().body("Devolução realizada com sucesso!");
            }
        }
        return ResponseEntity.badRequest().build();
    }
}
