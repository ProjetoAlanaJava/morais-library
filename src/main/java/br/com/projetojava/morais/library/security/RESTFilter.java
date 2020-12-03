package br.com.projetojava.morais.library.security;

import br.com.projetojava.morais.library.util.Constantes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

@Component
@Order(1)
@Slf4j
public class RESTFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        JWTAuthUtils auth = new JWTAuthUtils();

        log.info("Começando uma trasanção em : {}", req.getRequestURI());
        if(req.getRequestURI().equals(Constantes.URI_SINGIN_MATCH) || req.getRequestURI().equals(Constantes.URI_SINGUP_MATCH)) {
            chain.doFilter(request, response);
            log.info("Commitando uma trasanção em : {}", req.getRequestURI());

        } else {

            String token = req.getHeader("Authorization");

            if(Objects.nonNull(token)) {
                if(auth.verifyToken(token)) {
                    chain.doFilter(request, response);
                    log.info("Commitando uma trasanção em : {}", req.getRequestURI());
                } else {
                    response.getOutputStream().print("[NEGADO] Esse token não é válido");
                }


            } else {
                response.resetBuffer();
                response.setCharacterEncoding("UTF-8");
                response.getOutputStream().print("[NEGADO] Requesição sem token de identificação!");
                log.error("Requesição sem token de identificação!");
            }
        }
    }

    @Override
    public void destroy() {

    }
}
