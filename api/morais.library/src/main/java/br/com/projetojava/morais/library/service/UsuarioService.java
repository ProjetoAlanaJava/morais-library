package br.com.projetojava.morais.library.service;

import br.com.projetojava.morais.library.model.Usuario;
import br.com.projetojava.morais.library.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

   public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
   }

    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }

    public List<Usuario> findAll() {
       return usuarioRepository.findAll();
    }

    public Optional<Usuario> findById(Long id) {
        return usuarioRepository.findById(id);
    }
}
