package br.com.projetojava.morais.library.security;

import br.com.projetojava.morais.library.util.Constantes;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(0)
public class CORSFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletResponse res = (HttpServletResponse) response;

        res.setHeader("Access-Control-Allow-Origin", "*");
        res.setHeader("Access-Control-Allow-Credentials", "false");
        res.setHeader("Access-Control-Allow-Methods", Constantes.METODOS_PERMITIDOS);
        res.setHeader("Access-Control-Allow-Headers", Constantes.HEADERS_PERMITIDOS);
        res.setHeader("Access-Control-Max-Age", Constantes.MAX_AGE);

        if("OPTIONS".equalsIgnoreCase(((HttpServletRequest) request).getMethod())){
            res.setStatus(200);
        } else {
            chain.doFilter(request, res);
        }

    }

    @Override
    public void destroy() {

    }
}
