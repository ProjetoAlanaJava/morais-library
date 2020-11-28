package br.com.projetojava.morais.library.repository;

import br.com.projetojava.morais.library.model.ReservaLivro;
import br.com.projetojava.morais.library.model.books.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservaLivroRepository extends JpaRepository<ReservaLivro, Long> {
    ReservaLivro findByLivro(Livro livro);

    @Query(value = "SELECT * FROM reserva_livro WHERE usuario_id = ?", nativeQuery = true)
    List<ReservaLivro> findByUsuario(Long id);
}
