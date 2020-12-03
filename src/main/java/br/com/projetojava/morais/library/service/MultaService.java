package br.com.projetojava.morais.library.service;

import br.com.projetojava.morais.library.model.Multa;
import br.com.projetojava.morais.library.repository.MultaRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class MultaService {

    private MultaRepository repository;

    public MultaService(MultaRepository multaRepository) {
        this.repository = multaRepository;
    }

    public Multa save(Multa multa) {
        return repository.save(multa);
    }

    public Multa findByEmprestimo(Long emprestimoId) {
        return repository.findByEmprestimo(emprestimoId);
    }

    public Optional<Multa> findById(Long multaId) {
        return repository.findById(multaId);
    }

    public Multa findByid(Long multaId) {
        return repository.getOne(multaId);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public Integer calcularValorMulta(String dataDevolucaoEmprestimo) {
        LocalDate devolucao = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dataParaDevolver = LocalDate.from(formatter.parse(dataDevolucaoEmprestimo));
        Period period = Period.between(dataParaDevolver, devolucao);
        return period.getDays();
    }
}
