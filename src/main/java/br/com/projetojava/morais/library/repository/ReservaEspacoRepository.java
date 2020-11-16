package br.com.projetojava.morais.library.repository;

import br.com.projetojava.morais.library.model.ReservaEspaco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaEspacoRepository extends JpaRepository<ReservaEspaco, Long> {
}
