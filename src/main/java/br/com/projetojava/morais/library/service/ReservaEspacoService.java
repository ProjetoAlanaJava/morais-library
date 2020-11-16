package br.com.projetojava.morais.library.service;

import br.com.projetojava.morais.library.model.ReservaEspaco;
import br.com.projetojava.morais.library.repository.ReservaEspacoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaEspacoService {

    private final ReservaEspacoRepository repository;

    public ReservaEspacoService(ReservaEspacoRepository reservaEspacoRepository) {
        repository = reservaEspacoRepository;
    }

    public ReservaEspaco save(ReservaEspaco reserva) {
        return repository.save(reserva);
    }

    public List<ReservaEspaco> findAll() {
        return repository.findAll();
    }

    public Optional<ReservaEspaco> findById(Long id) {
        return repository.findById(id);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}
