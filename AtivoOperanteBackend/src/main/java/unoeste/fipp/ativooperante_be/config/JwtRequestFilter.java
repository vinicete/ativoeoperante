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

        // Obtém a requisição HTTP
        HttpServletRequest req = (HttpServletRequest) request;

        // Pega o token do cabeçalho Authorization
        String token = req.getHeader("Authorization");

        // Verifica se o token está presente e é válido
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7); // Remove o prefixo "Bearer "

            // Valida o token usando a classe JwtUtil
            if (JwtUtil.verifyToken(token)) {
                chain.doFilter(request, response);  // Se válido, permite a requisição
            } else {
                ((HttpServletResponse) response).setStatus(401);  // Retorna 401 se o token for inválido
                response.getOutputStream().write("Não autorizado".getBytes());
            }
        } else {
            // Caso o token não esteja presente ou não seja "Bearer", retorna 401
            ((HttpServletResponse) response).setStatus(401);  // Retorna 401 se não houver token
            response.getOutputStream().write("Token ausente ou mal formatado".getBytes());
        }
    }
}