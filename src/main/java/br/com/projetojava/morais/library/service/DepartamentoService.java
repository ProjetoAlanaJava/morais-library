package br.com.projetojava.morais.library.service;

import br.com.projetojava.morais.library.model.Departamento;
import br.com.projetojava.morais.library.model.Usuario;
import br.com.projetojava.morais.library.repository.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartamentoService {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    public Departamento save(Departamento departamento) {
        return departamentoRepository.save(departamento);
    }

    public List<Departamento> findAll() {
        return departamentoRepository.findAll();
    }

    public Optional<Departamento> findById(Long id) {
        return departamentoRepository.findById(id);
    }

    public void deleteById(Long id) {
       departamentoRepository.deleteById(id);
    }
}



