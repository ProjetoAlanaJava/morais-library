package br.com.projetojava.morais.library.controller;

import br.com.projetojava.morais.library.model.Departamento;
import br.com.projetojava.morais.library.service.DepartamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = {"/departamentos"})
public class DepartamentoController {

    private DepartamentoService departamentoService;

    public DepartamentoController( DepartamentoService departamentoService) {
        this.departamentoService = departamentoService;
    }

    @GetMapping
    public List findAll() {
        return departamentoService.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity findById(@PathVariable long id) {

        if(id > 0) {
            return departamentoService.findById(id)
                    .map(departamento -> {
                        departamentoService.findById(id);
                        return ResponseEntity.ok().body(departamento);
                    }).orElse(ResponseEntity.notFound().build());
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping
    public Departamento create(@RequestBody Departamento departamento) {
        if(departamento != null) {
            return departamentoService.save(departamento);
        }

        return null;
    }

    @PutMapping(path = {"/{id}"})
    public ResponseEntity update(@PathVariable long id, @RequestBody Departamento departamento) {

        if(departamento != null && id > 0) {
            return departamentoService.findById(id)
                    .map(dept-> {
                        dept.setNome(departamento.getNome());
                        dept.setArea(departamento.getArea());
                        Departamento novoDepartamento = departamentoService.save(dept);
                        return ResponseEntity.ok().body(novoDepartamento);
                    }).orElse(ResponseEntity.notFound().build());
        }

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity delete(@PathVariable long id) {

        if(id > 0) {
            return departamentoService.findById(id)
                    .map(departamento -> {
                        departamentoService.deleteById(id);
                        return ResponseEntity.ok().build();
                    }).orElse(ResponseEntity.notFound().build());

        }

        return ResponseEntity.badRequest().build();
    }

}
