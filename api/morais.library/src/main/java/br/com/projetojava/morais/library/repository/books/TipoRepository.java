package br.com.projetojava.morais.library.repository.books;

import br.com.projetojava.morais.library.model.books.Tipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoRepository extends JpaRepository<Tipo, Long> {
}
