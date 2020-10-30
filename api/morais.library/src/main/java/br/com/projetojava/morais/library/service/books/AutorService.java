package br.com.projetojava.morais.library.service.books;

import br.com.projetojava.morais.library.model.Usuario;
import br.com.projetojava.morais.library.model.books.Autor;
import br.com.projetojava.morais.library.repository.books.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    public List<Autor> findAll(){
        return autorRepository.findAll();
    }

    public Optional<Autor> findById(Long id){
        return autorRepository.findById(id);
    }

}
