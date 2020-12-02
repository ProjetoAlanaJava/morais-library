package br.com.projetojava.morais.library.repository;

import br.com.projetojava.morais.library.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {

    @Query(value = "SELECT COUNT(*) FROM eventos", nativeQuery = true)
    Integer countById();

    @Query(value = "SELECT COUNT(*) FROM eventos WHERE `date` = ?", nativeQuery = true)
    Integer countByDate(String dataEvento);

    @Query(value = "SELECT COUNT(*) FROM eventos WHERE `status` = ?", nativeQuery = true)
    Integer countByStatus(String stauts);

}
