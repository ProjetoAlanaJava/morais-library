package br.com.projetojava.morais.library.service;

import br.com.projetojava.morais.library.model.Evento;
import br.com.projetojava.morais.library.repository.EventoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventoService {

    private final EventoRepository repository;

    public EventoService(EventoRepository eventoRepository) {
        this.repository = eventoRepository;
    }

    public List<Evento> findAll() {
        return repository.findAll();
    }

    public Integer countAll() {
        return repository.countById();
    }

    public Integer countByData(String dataEvento) {
        return repository.countByDate(dataEvento);
    }

    public Integer countByStatus(String status) {
        return repository.countByStatus(status);
    }

    public Optional<Evento> findById(Long id) {
        return repository.findById(id);
    }

    public Evento save(Evento evento) {
        return repository.save(evento);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }


}
