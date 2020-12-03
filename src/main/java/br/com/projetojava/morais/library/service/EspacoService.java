package br.com.projetojava.morais.library.service;

import br.com.projetojava.morais.library.model.Espaco;
import br.com.projetojava.morais.library.repository.EspacoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EspacoService {

    EspacoRepository repository;

    public EspacoService(EspacoRepository espacoRepository) {
        repository = espacoRepository;
    }

    public Espaco save(Espaco espaco) {
        return repository.save(espaco);
    }

    public List<Espaco> findAll() {
        return repository.findAll();
    }

    public Optional<Espaco> findById(Long id) {
        return repository.findById(id);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
