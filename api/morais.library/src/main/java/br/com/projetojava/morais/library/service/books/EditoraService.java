package br.com.projetojava.morais.library.service.books;

import br.com.projetojava.morais.library.model.books.Editora;
import br.com.projetojava.morais.library.repository.books.EditoraRepository;
import br.com.projetojava.morais.library.service.books.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EditoraService {

    @Autowired
    private EditoraRepository editoraRepository;

    public Editora save(Editora editora){
        return editoraRepository.save(editora);
    }


    public List<Editora> findAll(){
        return editoraRepository.findAll();
    }

    public Editora findById(Long id){
        Optional<Editora> editora = editoraRepository.findById(id);
        return editora.orElseThrow(() -> new ObjectNotFoundException("Object not found"));
    }

    public Editora create( Editora editora){
        return  editoraRepository.save(editora);
    }


    public Editora update(Editora editora){
        Editora newEditora = findById(editora.getId());

        updateData(newEditora, editora);
        return save(editora);
    }

    public void updateData(Editora newEditora, Editora editora){
        newEditora.setNome(editora.getNome());
        newEditora.setCnpj(editora.getCnpj());
        newEditora.setEmail(editora.getEmail());
        newEditora.setPais(editora.getPais());
        newEditora.setTelefone(editora.getTelefone());
    }

    public void deleteById(Long id){
        findById(id);
        editoraRepository.deleteById(id);
    }
}
