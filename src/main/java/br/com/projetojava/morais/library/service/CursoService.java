package br.com.projetojava.morais.library.service;

import br.com.projetojava.morais.library.model.Curso;
import br.com.projetojava.morais.library.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public Curso save(Curso curso) {
        return cursoRepository.save(curso);
    }

    public void deleteById(Long id) {
        cursoRepository.deleteById(id);
    }

    public Optional<Curso> findById(Long id) {
        return cursoRepository.findById(id);
    }

    public List<Curso> findAll() {
        return cursoRepository.findAll();
    }

}

