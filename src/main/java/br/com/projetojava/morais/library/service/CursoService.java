package br.com.projetojava.morais.library.service;

import br.com.projetojava.morais.library.model.Curso;
import br.com.projetojava.morais.library.repository.CursoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    private final CursoRepository repository;

    public CursoService(CursoRepository cursoRepository){
        repository = cursoRepository;
    }

    public Curso save(Curso curso) {
        return repository.save(curso);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public Optional<Curso> findById(Long id) {
        return repository.findById(id);
    }

    public List<Curso> findAll() {
        return repository.findAll();
    }

}

