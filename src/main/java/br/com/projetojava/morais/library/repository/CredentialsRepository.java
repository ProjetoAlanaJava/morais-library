package br.com.projetojava.morais.library.repository;

import br.com.projetojava.morais.library.model.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface CredentialsRepository extends JpaRepository<UserCredentials, Long> {

    Optional<UserCredentials> findByuserNameAndPassWord(String username, String password);

    List<UserCredentials> findAll();
}
