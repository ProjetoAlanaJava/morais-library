package br.com.projetojava.morais.library.service;

import br.com.projetojava.morais.library.model.Emprestimo;
import br.com.projetojava.morais.library.model.books.Livro;
import br.com.projetojava.morais.library.repository.EmprestimoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class EmprestimoService {

    EmprestimoRepository repository;

    public EmprestimoService(EmprestimoRepository emprestimoRepository) {
        repository = emprestimoRepository;
    }

    public List<Emprestimo> findByUsuario(Long usuarioID) {
        return repository.findByUsuario(usuarioID);
    }
    public Optional<Emprestimo> findByid(Long id) {
        return repository.findById(id);
    }

    public Emprestimo findById(Long id) {
        return repository.getOne(id);
    }

    public Emprestimo save(Emprestimo emprestimo) {
        return repository.save(emprestimo);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Integer countAllEmprestimos() {
        return repository.countById();
    }

    public Integer countByDataDevolucao(String dataDevolucao) {
        return repository.countByDataDevolucao(dataDevolucao);
    }

    public boolean verificarValido(String matriculaUsuario, String matriculaReserva, String dataMaximaReserva){

        boolean matriculaIguais = matriculaUsuario.equals(matriculaReserva);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dataAtual = LocalDate.now();
        LocalDate dataReserva = LocalDate.from(formatter.parse(dataMaximaReserva));
        boolean dataDepois = dataAtual.isAfter(dataReserva);

        if(matriculaIguais && !dataDepois) {
            return true; //mesmo usuario e ainda está dentro do prazo
        } else if(!matriculaIguais && dataDepois) {
            return true; //outro usuario tentando o emprestimo, mas a reserva do usuario origial já está vencida!!!!!!!!
        } else if(!matriculaIguais && !dataDepois) {
            return false; //usuario diferente, data dentro do prazo, então não pode ser emprestado!!!!!
        }

        return false;
    }

    public boolean verificarDataDevolucao(String dataDevolucao) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dataAtual = LocalDate.now();
        LocalDate devolucao = LocalDate.from(formatter.parse(dataDevolucao));
        return dataAtual.isAfter(devolucao);
    }

}
