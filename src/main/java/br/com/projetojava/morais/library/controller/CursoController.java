package br.com.projetojava.morais.library.controller;

import br.com.projetojava.morais.library.model.Curso;
import br.com.projetojava.morais.library.repository.CursoRepository;
import br.com.projetojava.morais.library.service.CursoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = {"/cursos"})
public class CursoController {

    private CursoService cursoService;

    public CursoController(CursoService cursoService){
        this.cursoService = cursoService;
    }

    @GetMapping
    public List<Curso> findAll() {
        return cursoService.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity findById(@PathVariable long id) {

        if(id > 0) {
            return cursoService.findById(id)
                    .map(curso -> {
                        cursoService.findById(id);
                        return ResponseEntity.ok().body(curso);
                    }).orElse(ResponseEntity.notFound().build());
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping
    public Curso create(@RequestBody Curso curso) {
        if(curso != null) {
            return cursoService.save(curso);
        }

        return null;
    }

    @PutMapping(path = {"/{id}"})
    public ResponseEntity update(@PathVariable long id, @RequestBody Curso curso) {

        if(curso != null && id > 0) {
            return cursoService.findById(id)
                    .map(course -> {
                        course.setNome(curso.getNome());
                        course.setArea(curso.getArea());
                        course.setTipo(curso.getTipo());
                        Curso novoCurso = cursoService.save(course);
                        return ResponseEntity.ok().body(novoCurso);
                    }).orElse(ResponseEntity.notFound().build());
        }

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<Object> delete(@PathVariable long id) {

        if(id > 0) {
            return cursoService.findById(id)
                    .map(curso -> {
                        cursoService.deleteById(id);
                        return ResponseEntity.ok().build();
                    }).orElse(ResponseEntity.notFound().build());
        }

        return ResponseEntity.badRequest().build();
    }

}
