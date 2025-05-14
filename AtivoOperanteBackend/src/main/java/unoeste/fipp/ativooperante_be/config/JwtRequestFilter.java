package unoeste.fipp.ativooperante_be.config;

import io.jsonwebtoken.Jwt;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
public class JwtRequestFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String token = req.getHeader("Authorization");
        if(token!=null && JwtUtil.verifyToken(token))
            chain.doFilter(request, response);
        else {
            ((HttpServletResponse) response).setStatus(500);
            response.getOutputStream().write("Não autorizado ".getBytes());
        }
    }
}