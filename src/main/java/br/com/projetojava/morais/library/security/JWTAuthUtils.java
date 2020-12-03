package br.com.projetojava.morais.library.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

@Component
public class JWTAuthUtils{

    public JWTAuthUtils() throws IOException {
    }

    private final String secret = getSecret();
    Algorithm algo = Algorithm.HMAC256(secret);

    public String createToken(String subjectOfToken) {
        Date today = new Date();
        Date dateToExpire = new Date(today.getTime() + (1000 * 60 * 60 * 24));
        return  JWT.create()
                .withSubject(subjectOfToken)
                .withExpiresAt(dateToExpire)
                .sign(algo);
    }

    public boolean verifyToken(String token) {
        JWTVerifier verifier = JWT.require(algo)
                .build();
        DecodedJWT jwt = verifier.verify(token);
        return !jwt.getSubject().isEmpty() && !tokenIsExpired(jwt.getExpiresAt());
    }

    public Boolean tokenIsExpired(Date dateTokenExpire) {
        return dateTokenExpire.before(new Date());
    }

    private String getSecret() throws IOException {
        Properties prop = getProp();
        return prop.getProperty("jwt.secret");
    }

    private static Properties getProp() throws IOException {
        Properties props = new Properties();
        FileInputStream file = new FileInputStream("./system.properties");
        props.load(file);
        return props;

    }
}

