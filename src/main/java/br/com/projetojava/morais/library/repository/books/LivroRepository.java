package br.com.projetojava.morais.library.repository.books;

import br.com.projetojava.morais.library.model.books.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
}
