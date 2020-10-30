package br.com.projetojava.morais.library.service.books;

import br.com.projetojava.morais.library.model.books.*;
import br.com.projetojava.morais.library.repository.books.*;
import br.com.projetojava.morais.library.service.books.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private EditoraRepository editoraRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private TipoRepository tipoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Livro save( Livro livro){
        return livroRepository.save(livro);
    }

    public List<Livro> findAll(){
        return livroRepository.findAll();
    }

    public Livro findById( Long id){
        Optional<Livro> livro = livroRepository.findById(id);

        return livro.orElseThrow(() -> new ObjectNotFoundException("Object not found"));
    }


    public Livro create( Livro livro){
        Editora editora = editoraRepository.findById(livro.getEditora().getId())
                .orElseThrow();

//        List<Autor> autores = (List<Autor>) livro.getAutores().stream().map(autor ->
//                autorRepository.findById(autor.getId()).orElseThrow());

        Tipo tipo = tipoRepository.findById(livro.getTipo().getId())
                .orElseThrow();

        Categoria categoria = categoriaRepository.findById(livro.getCategoria().getId())
                .orElseThrow();

        livro.setEditora(editora);
        livro.setTipo(tipo);
        livro.setCategoria(categoria);

        return livroRepository.save(livro);
    }

    public Livro update(Livro livro){
        Livro newLivro = findById(livro.getId());

        updateData(newLivro, livro);
        return save(livro);
    }

    public void updateData(Livro newLivro, Livro livro){
        newLivro.setTitulo(livro.getTitulo());
        newLivro.setEditora(livro.getEditora());
        newLivro.setTipo(livro.getTipo());
        newLivro.setAno_publicacao(livro.getAno_publicacao());
        newLivro.setAutores(livro.getAutores());
        newLivro.setIsbn(livro.getIsbn());
        newLivro.setQtd_geral(livro.getQtd_geral());
        newLivro.setAssunto(livro.getAssunto());
        newLivro.setCategoria(livro.getCategoria());
    }

    public void deleteById(Long id){
        findById(id);
        livroRepository.deleteById(id);
    }

}
