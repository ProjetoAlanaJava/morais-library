package br.com.projetojava.morais.library.service.books;

import br.com.projetojava.morais.library.model.books.Autor;
import br.com.projetojava.morais.library.model.books.Editora;
import br.com.projetojava.morais.library.repository.books.EditoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EditoraService {

    @Autowired
    private EditoraRepository editoraRepository;


    public List<Editora> findAll(){
        return editoraRepository.findAll();
    }

    public Optional<Editora> findById(Long id){
        return editoraRepository.findById(id);
    }
}
