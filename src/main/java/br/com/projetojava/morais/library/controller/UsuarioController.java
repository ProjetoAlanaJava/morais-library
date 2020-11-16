package br.com.projetojava.morais.library.controller;


import br.com.projetojava.morais.library.model.Usuario;
import br.com.projetojava.morais.library.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/usuarios"})
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    /** [DEPRECIADO] Usar o metodo que esta no AuthController!!!
    @GetMapping
    public List<Usuario> findAll() {
        return usuarioService.findAll();
    }**/

    /** [DEPRECIADO] Usar o metodo que esta na AuthController
    @GetMapping(path = {"/{id}"})
    public ResponseEntity<Usuario> findById(@PathVariable long id) {

        Usuario rUser = new Usuario();

        if(id > 0) {
            return usuarioService.findById(id)
                    .map(user -> {
                        rUser.setId(user.getId());
                        rUser.setMatricula(user.getMatricula());
                        rUser.setAuthority(user.getAuthority());
                        rUser.setNome(user.getNome());
                        rUser.setCpf(user.getCpf());
                        rUser.setAtivo(user.getAtivo());
                        rUser.setCurso(user.getCurso());
                        rUser.setCargo(user.getCargo());
                        rUser.setLimiteLivros(user.getLimiteLivros());
                        rUser.setTelefone(user.getTelefone());
                        rUser.setEmail(user.getEmail());

                        return ResponseEntity.ok().body(rUser);
                    })
                    .orElse(ResponseEntity.notFound().build());
        }
        return ResponseEntity.badRequest().build();
    }**/

    /**
    [DEPRECIADO] Usar o Post em AuthController de signup para criar novos usuarios!!!
    @PostMapping
    public Usuario create(@RequestBody Usuario usuario) {

        if(usuario != null) {
            return usuarioService.save(usuario);
        }

        return null;

    }**/

    /**
     * [DEPRECIADO] Usar o PUT em AuthController de update para atualizar os usuarios!!!
    @PutMapping(value = {"/{id}"})
    public ResponseEntity<Usuario> update(@PathVariable("id") long id, @RequestBody Usuario usuario){

        if(usuario != null && id > 0) {
            return usuarioService.findById(id)
                    .map(user -> {
                        user.setNome(usuario.getNome());
                        user.setCpf(usuario.getCpf());
                        user.setAtivo(usuario.getAtivo());
                        user.setCurso(usuario.getCurso());
                        user.setDepartamento(usuario.getDepartamento());
                        user.setCargo(usuario.getCargo());
                        user.setLimiteLivros(usuario.getLimiteLivros());
                        user.setTelefone(usuario.getTelefone());
                        user.setEmail(usuario.getEmail());
                        Usuario atualizado = usuarioService.save(user);
                        return ResponseEntity.ok().body(atualizado);
                    }).orElse(ResponseEntity.notFound().build());
        }

       return ResponseEntity.badRequest().build();
    }
     **/

    /**
     * [DEPRECIADO] Usar o DELETE em AuthController para deletar os usuarios!!!
    @DeleteMapping(value = {"/{id}"})
    public ResponseEntity<Object> delete(@PathVariable long id){

        if(id > 0) {
            usuarioService.findById(id)
                    .map(user -> {
                        usuarioService.deleteById(id);
                        return ResponseEntity.ok().build();
                    }).orElse(ResponseEntity.notFound().build());

        }

        return ResponseEntity.badRequest().build();

    }*/
}
