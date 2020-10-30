package br.com.projetojava.morais.library.service.books;

import br.com.projetojava.morais.library.model.books.Livro;
import br.com.projetojava.morais.library.model.books.Tipo;
import br.com.projetojava.morais.library.repository.books.TipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoService {

    @Autowired
    TipoRepository tipoRepository;

    public List<Tipo> findAll(){
        return tipoRepository.findAll();
    }

    public Optional<Tipo> findById(Long id){
        return tipoRepository.findById(id);
    }

}

