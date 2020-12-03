package br.com.projetojava.morais.library.repository;

import br.com.projetojava.morais.library.model.ReservaEspaco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservaEspacoRepository extends JpaRepository<ReservaEspaco, Long> {

    @Query(value = "SELECT * FROM reserva_espaco where data = ? AND horario_inicio_reserva = ? AND horario_fim_reserva = ?", nativeQuery = true)
    ReservaEspaco findByDataAndhorarioInicioReservaAndHorarioFimReserva(Date data, Date horarioInicioReserva, Date horarioFimReserva);

    @Query(value = "SELECT COUNT(*) FROM reserva_espaco", nativeQuery = true)
    Integer countAllById();

    @Query(value = "SELECT COUNT(*) FROM reserva_espaco WHERE `data` = ? ", nativeQuery = true)
    Integer countByData(String dataMaximoEmprestimo);

    @Query(value = "SELECT * FROM reserva_espaco WHERE usuario_id = ?", nativeQuery = true)
    List<ReservaEspaco> findByUsuario(Long id);

}
