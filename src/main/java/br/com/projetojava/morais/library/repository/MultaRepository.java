package br.com.projetojava.morais.library.repository;

import br.com.projetojava.morais.library.model.Multa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MultaRepository extends JpaRepository<Multa, Long> {

    @Query(value = "SELECT * FROM multa WHERE emprestimo_id = ?", nativeQuery = true)
    Multa findByEmprestimo(Long id);
}
