package br.com.projetojava.morais.library.service.books;

import br.com.projetojava.morais.library.model.Usuario;
import br.com.projetojava.morais.library.model.books.Autor;
import br.com.projetojava.morais.library.model.books.Categoria;
import br.com.projetojava.morais.library.repository.books.AutorRepository;
import br.com.projetojava.morais.library.service.books.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    public Autor save(Autor autor){
        return autorRepository.save(autor);
    }

    public List<Autor> findAll(){
        return autorRepository.findAll();
    }

    public Autor findById(Long id){
        Optional<Autor> autor = autorRepository.findById(id);
        return autor.orElseThrow(()-> new ObjectNotFoundException("Object not found!"));
    }

    public Autor create(Autor autor){
        return  autorRepository.save(autor);
    }


    public Autor update(Autor autor){
        Autor newAutor = findById(autor.getId());

        updateData(newAutor, autor);
        return save(autor);
    }

    public void updateData(Autor newAutor, Autor autor){
        newAutor.setName(autor.getName());
        newAutor.setBio(autor.getBio());
        newAutor.setPais(autor.getPais());
    }

    public void deleteById(Long id){
        findById(id);
        autorRepository.deleteById(id);
    }
}
