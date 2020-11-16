package br.com.projetojava.morais.library.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserCredentials {

    private String username;

    private String password;

}
/** Modelo de Requisição para fazer Login!
    {
        "username": "20191022024",
        "password": "123456"
    }

**/