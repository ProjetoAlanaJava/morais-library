package br.com.projetojava.morais.library.controller;

import br.com.projetojava.morais.library.model.Curso;
import br.com.projetojava.morais.library.service.CursoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(path = {"/cursos"})
public class CursoController {

    private final CursoService service;

    public CursoController(CursoService cursoService) {
        service = cursoService;
    }

    @GetMapping
    public List<Curso> findAll() {
        return service.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<Curso> findById(@PathVariable long id) {

        if(id > 0) {
            return service.findById(id)
                    .map(curso -> {
                        service.findById(id);
                        return ResponseEntity.ok().body(curso);
                    }).orElse(ResponseEntity.notFound().build());
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping
    public Curso create(@RequestBody Curso curso) {
        if(curso != null) {
            return service.save(curso);
        }

        return null;
    }

    @PutMapping(path = {"/{id}"})
    public ResponseEntity<Curso> update(@PathVariable long id, @RequestBody Curso curso) {

        if(Objects.nonNull(curso) && id > 0) {
            return service.findById(id)
                    .map(course -> {
                        course.setNome(curso.getNome());
                        course.setArea(curso.getArea());
                        course.setTipo(curso.getTipo());
                        Curso novoCurso = service.save(course);
                        return ResponseEntity.ok().body(novoCurso);
                    }).orElse(ResponseEntity.notFound().build());
        }

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<Object> delete(@PathVariable long id) {

        if(id > 0) {
            return service.findById(id)
                    .map(curso -> {
                        service.deleteById(id);
                        return ResponseEntity.ok().build();
                    }).orElse(ResponseEntity.notFound().build());
        }

        return ResponseEntity.badRequest().build();
    }

}
