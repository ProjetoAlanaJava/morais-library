package br.com.projetojava.morais.library.model;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@DynamicInsert
@DynamicUpdate
@EqualsAndHashCode(of = { "id" })
@ToString(of = {"id"})
@Table(name = "logins")
public class UserCredentials {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true)
    private String userName;

    @Column(name = "password")
    private String passWord;

    //GrantedAuthority
    private String authority;

}
