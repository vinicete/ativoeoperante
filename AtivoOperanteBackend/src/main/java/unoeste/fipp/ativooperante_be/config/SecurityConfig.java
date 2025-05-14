package unoeste.fipp.ativooperante_be.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtRequestFilter jwtFilter;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .requestMatchers("/apis/login/signin", "/apis/login/signup").permitAll()  // Permite acesso ao login e signup
                .anyRequest().authenticated()  // Requer autenticação para qualquer outra requisição
                .and().addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);  // Adiciona o filtro JWT

        return http.build();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception { //metodo que permite rodar endpoints com / ou /login sem autenticação padrao, mas sim customizada
        http.csrf().disable()
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()
                );
        return http.build();
    }
}