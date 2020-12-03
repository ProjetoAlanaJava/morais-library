package br.com.projetojava.morais.library.service.books;

import br.com.projetojava.morais.library.model.books.Categoria;
import br.com.projetojava.morais.library.repository.books.CategoriaRepository;
import br.com.projetojava.morais.library.service.books.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria save(Categoria categoria){
        return categoriaRepository.save(categoria);
    }


    public List<Categoria> findAll(){
        return categoriaRepository.findAll();
    }

    public Categoria findById(Long id){
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        return categoria.orElseThrow(()-> new ObjectNotFoundException("Object not found"));
    }

    public Categoria create(Categoria categoria){
        return  categoriaRepository.save(categoria);
    }


    public Categoria update(Categoria categoria){
        Categoria newCategoria = findById(categoria.getId());

        updateData(newCategoria, categoria);
        return save(categoria);
    }

    public void updateData(Categoria newCategoria , Categoria categoria){
        newCategoria.setNome(categoria.getNome());
        newCategoria.setDescricao(categoria.getDescricao());

    }

    public void deleteById(Long id){
        findById(id);
        categoriaRepository.deleteById(id);
    }
}
