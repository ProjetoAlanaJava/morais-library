package br.com.projetojava.morais.library.repository;

import br.com.projetojava.morais.library.model.FichaCatalografica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FichaCatalograficaRepository extends JpaRepository<FichaCatalografica, Long> {


    @Query(value = "SELECT * FROM ficha_catalografica AS f INNER JOIN autores AS a On a.id = f.autor_id WHERE a.name = ?", nativeQuery = true)
    Optional<FichaCatalografica> findByAutor(String autor);

    @Query(value = "SELECT * FROM ficha_catalografica WHERE titulo = ?", nativeQuery = true)
    Optional<FichaCatalografica> findByTitulo(String titulo);

}
