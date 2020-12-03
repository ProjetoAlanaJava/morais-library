package br.com.projetojava.morais.library.repository;

import br.com.projetojava.morais.library.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByMatriculaAndPassword(String username, String password);

    Usuario findByMatricula(String matricula);

    @Query(value = "SELECT COUNT(*) FROM usuarios", nativeQuery = true)
    Integer countByAtivo();

    @Query(value = "SELECT COUNT(*) FROM usuarios WHERE authority = ?" , nativeQuery = true)
    Integer countAllByAuthority(String authority);

}
