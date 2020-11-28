package br.com.projetojava.morais.library.service;

import br.com.projetojava.morais.library.model.ReservaLivro;
import br.com.projetojava.morais.library.model.books.Livro;
import br.com.projetojava.morais.library.repository.ReservaLivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaLivroService {

    private ReservaLivroRepository repository;

    public ReservaLivroService(ReservaLivroRepository reservaLivroRepository) {
        this.repository =  reservaLivroRepository;
    }
    public List<ReservaLivro> findByUsuario(Long usuarioId) {
        return repository.findByUsuario(usuarioId);
    }
    public ReservaLivro findByLivro(Livro livroReserva) {
        return repository.findByLivro(livroReserva);
    }

    public Optional<ReservaLivro> findById(Long resveraID) {
        return repository.findById(resveraID);
    }

    public ReservaLivro save(ReservaLivro reservaDeLivro) {
        return repository.save(reservaDeLivro);
    }

    public void deleteById(Long reservaID) {
        repository.deleteById(reservaID);
    }

}
