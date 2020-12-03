package br.com.projetojava.morais.library.controller;

import br.com.projetojava.morais.library.model.Departamento;
import br.com.projetojava.morais.library.service.DepartamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = {"/departamentos"})
public class DepartamentoController {


    private final DepartamentoService service;

    public DepartamentoController(DepartamentoService departamentoService) {
        service = departamentoService;
    }

    @GetMapping
    public List<Departamento> findAll() {
        return service.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<Departamento> findById(@PathVariable long id) {

        if(id > 0) {
            return service.findById(id)
                    .map(departamento -> {
                        service.findById(id);
                        return ResponseEntity.ok().body(departamento);
                    }).orElse(ResponseEntity.notFound().build());
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping
    public Departamento create(@RequestBody Departamento departamento) {
        if(departamento != null) {
            return service.save(departamento);
        }

        return null;
    }

    @PutMapping(path = {"/{id}"})
    public ResponseEntity<Departamento> update(@PathVariable long id, @RequestBody Departamento departamento) {

        if(departamento != null && id > 0) {
            return service.findById(id)
                    .map(dept-> {
                        dept.setNome(departamento.getNome());
                        Departamento novoDepartamento = service.save(dept);
                        return ResponseEntity.ok().body(novoDepartamento);
                    }).orElse(ResponseEntity.notFound().build());
        }

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<Object> delete(@PathVariable long id) {

        if(id > 0) {
            return service.findById(id)
                    .map(departamento -> {
                        service.deleteById(id);
                        return ResponseEntity.ok().build();
                    }).orElse(ResponseEntity.notFound().build());

        }

        return ResponseEntity.badRequest().build();
    }

}
