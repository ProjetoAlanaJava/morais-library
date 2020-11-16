package br.com.projetojava.morais.library.service.books;

import br.com.projetojava.morais.library.model.books.Editora;
import br.com.projetojava.morais.library.model.books.Livro;
import br.com.projetojava.morais.library.model.books.Tipo;
import br.com.projetojava.morais.library.repository.books.TipoRepository;
import br.com.projetojava.morais.library.service.books.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoService {

    @Autowired
    TipoRepository tipoRepository;

    public Tipo save(Tipo tipo){
        return tipoRepository.save(tipo);
    }

    public List<Tipo> findAll(){
        return tipoRepository.findAll();
    }

    public Tipo findById(Long id){
        Optional<Tipo> tipo = tipoRepository.findById(id);
        return tipo.orElseThrow(()-> new ObjectNotFoundException("Object not found!"));
    }


    public Tipo create(Tipo tipo){
        return  tipoRepository.save(tipo);
    }


    public Tipo update(Tipo tipo){
        Tipo newTipo = findById(tipo.getId());

        updateData(newTipo, tipo);
        return save(tipo);
    }

    public void updateData( Tipo newTipo, Tipo tipo){
        newTipo.setNome(tipo.getNome());
    }

    public void deleteById(Long id){
        findById(id);
        tipoRepository.deleteById(id);
    }
}

