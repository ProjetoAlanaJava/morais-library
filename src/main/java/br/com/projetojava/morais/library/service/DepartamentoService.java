package br.com.projetojava.morais.library.service;

import br.com.projetojava.morais.library.model.Departamento;
import br.com.projetojava.morais.library.repository.DepartamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartamentoService {

    private final DepartamentoRepository repository;

    public DepartamentoService(DepartamentoRepository departamentoRepository) {
        repository = departamentoRepository;
    }

    public Departamento save(Departamento departamento) {
        return repository.save(departamento);
    }

    public List<Departamento> findAll() {
        return repository.findAll();
    }

    public Optional<Departamento> findById(Long id) {
        return repository.findById(id);
    }

    public void deleteById(Long id) {
       repository.deleteById(id);
    }
}



