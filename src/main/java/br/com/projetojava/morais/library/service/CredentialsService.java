package br.com.projetojava.morais.library.service;

import br.com.projetojava.morais.library.model.UserCredentials;
import br.com.projetojava.morais.library.repository.CredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CredentialsService {

    @Autowired
    private CredentialsRepository credentialsRepository;

    public Optional<UserCredentials> findByUserAndPass(String user, String password) {
        return credentialsRepository.findByuserNameAndPassWord(user, password);
    }

    public List<UserCredentials> findAll(){
        return credentialsRepository.findAll();
    }

    public UserCredentials save(UserCredentials user) {
        return credentialsRepository.save(user);
    }

}
