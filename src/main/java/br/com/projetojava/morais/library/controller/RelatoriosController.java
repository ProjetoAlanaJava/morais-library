package br.com.projetojava.morais.library.controller;

import br.com.projetojava.morais.library.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;

@RestController
@RequestMapping({"/gerar-relatorios"})
public class RelatoriosController {

    private final UsuarioService usuarioService;
    private final EmprestimoService emprestimoService;
    private final ReservaLivroService reservaLivroService;
    private final ReservaEspacoService reservaEspacoService;
    private final EventoService eventoService;

    public RelatoriosController(UsuarioService usuarioServico, EmprestimoService emprestimoServico, ReservaLivroService
            reservaLivroServico, ReservaEspacoService reservaEspacoServico, EventoService eventoServico) {
        usuarioService = usuarioServico;
        emprestimoService = emprestimoServico;
        reservaLivroService = reservaLivroServico;
        reservaEspacoService = reservaEspacoServico;
        eventoService = eventoServico;
    }


    /**
     * Método que retornar relatorios do sistema de acordo com o dia atual em que o relatório é solicitado
     * @return 200 - Json no body com informaçoes.
     */

    @GetMapping
    @ResponseBody
    public ResponseEntity<Object> gerarRelatorios() {

        DateTimeFormatter formatarData = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter formatarDataUSA = DateTimeFormatter.ofPattern("yyyy-dd-MM");
        LocalDate dataHoje = LocalDate.now();
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();

        map.put("Total usuarios cadastrados no sistema: ", usuarioService.countAllUsers());
        map.put("Total professores cadastrados no sistema: ", usuarioService.countAllUsersByAutority("professor"));
        map.put("Total alunos cadastrados no sistema: ", usuarioService.countAllUsersByAutority("aluno"));
        map.put("Total usuarios externos cadastrados no sistema: ", usuarioService.countAllUsersByAutority("externo"));
        map.put("Total funcionarios cadastrados no sistema: ", usuarioService.countAllUsersByAutority("funcionario"));
        map.put("Total emprestimos no sistema: ", emprestimoService.countAllEmprestimos());
        map.put("Total emprestimos no sistema na data " + formatarData.format(dataHoje), emprestimoService.countByDataDevolucao(formatarData.format(dataHoje)));
        map.put("Total reservas de livros no sistema: ", reservaLivroService.countALl());
        map.put("Total  reservas de livros no sistema na data " + formatarData.format(dataHoje), reservaLivroService.countALlByData(formatarData.format(dataHoje)));
        map.put("Total reservas de espacos no sistema: ", reservaEspacoService.countALl());
        map.put("Total  reservas de espacos no sistema na data " + formatarData.format(dataHoje), reservaEspacoService.countByData(formatarDataUSA.format(dataHoje)));
        map.put("Total  eventos no sistema: ", eventoService.countAll());
        map.put("Total  eventos no sistema na data " + formatarData.format(dataHoje), eventoService.countByData(formatarDataUSA.format(dataHoje)));
        map.put("Total  eventos no sistema com status cancelado ", eventoService.countByStatus("cancelado"));

        return ResponseEntity.ok().body(map);
    }
}