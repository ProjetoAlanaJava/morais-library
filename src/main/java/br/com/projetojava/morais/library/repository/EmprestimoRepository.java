package br.com.projetojava.morais.library.repository;

import br.com.projetojava.morais.library.model.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

    @Query(value = "SELECT * FROM emprestimo WHERE usuario_id = ?", nativeQuery = true)
    List<Emprestimo> findByUsuario(Long id);

    @Query(value = "SELECT COUNT(*) FROM emprestimo", nativeQuery = true)
    Integer countById();

    @Query(value = "SELECT COUNT(*) FROM emprestimo WHERE data_devolucao = ?", nativeQuery = true)
    Integer countByDataDevolucao(String dataDevolucao);
}
