package br.com.projetojava.morais.library.service;

import br.com.projetojava.morais.library.model.Usuario;
import br.com.projetojava.morais.library.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        repository = usuarioRepository;
    }

    public Usuario save(Usuario usuario) {
        return repository.save(usuario);
    }

    public Optional<Usuario> findByUserAndPass(String user, String password) {
        return repository.findByMatriculaAndPassword(user, password);
    }

    public Optional<Usuario> findById(Long id) {
        return repository.findById(id);
    }

    public List<Usuario> findAll() {
        return repository.findAll();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}
