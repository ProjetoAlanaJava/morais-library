package br.com.projetojava.morais.library.security;

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

        log.info("Começando uma trasanção em : {}", req.getRequestURI());
        String token = req.getHeader("access_token");
        if(Objects.nonNull(token) && !token.isEmpty()) {
            chain.doFilter(request, response);
            log.info("Commitando uma trasanção em : {}", req.getRequestURI());

        } else {
            response.resetBuffer();
            response.setCharacterEncoding("UTF-8");
            response.getOutputStream().print("[NEGADO] Requesição sem token de identificação!");
            log.error("Requesição sem token de identificação!");
        }

    }

    @Override
    public void destroy() {

    }
}
