package br.com.projetojava.morais.library.service;

import br.com.projetojava.morais.library.model.FichaCatalografica;
import br.com.projetojava.morais.library.repository.FichaCatalograficaRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FichaCatalograficaService {

    FichaCatalograficaRepository repository;

    public FichaCatalograficaService(FichaCatalograficaRepository fichaCatalograficaRepository) {
        repository = fichaCatalograficaRepository;
    }

    public List<FichaCatalografica> findAll() {
        return repository.findAll();
    }

    public FichaCatalografica save(FichaCatalografica ficha) {
        return repository.save(ficha);
    }

    public Optional<FichaCatalografica> findById(Long id) {
        return repository.findById(id);
    }

    public Optional<FichaCatalografica> findByTitle(String title) {
        return repository.findByTitulo(title);
    }

    public Optional<FichaCatalografica> findByAuthor(String autor) {
        return repository.findByAutor(autor);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
