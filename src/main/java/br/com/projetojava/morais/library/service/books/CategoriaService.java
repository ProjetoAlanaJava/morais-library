package br.com.projetojava.morais.library.service.books;

import br.com.projetojava.morais.library.model.books.Autor;
import br.com.projetojava.morais.library.model.books.Categoria;
import br.com.projetojava.morais.library.repository.books.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;


    public List<Categoria> findAll(){
        return categoriaRepository.findAll();
    }

    public Optional<Categoria> findById(Long id){
        return categoriaRepository.findById(id);
    }

}
