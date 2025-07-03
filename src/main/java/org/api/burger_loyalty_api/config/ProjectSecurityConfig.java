package org.api.burger_loyalty_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)

                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/clients/**").hasRole("ADMIN")
                        .requestMatchers("/client/**").hasRole("CLIENT")
                        .requestMatchers("/auth/register","/auth/login").permitAll()
                );

                /*
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/clients/**","/client/**").authenticated()
                        .requestMatchers("/register").permitAll()
                );
                */
        http.formLogin(withDefaults());
        http.httpBasic(withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder(); // Agrega siempre el Password recomendado por Spring Boot
    }

    /**
     * From Spring 6.3
     * @return
     */
    // Verifica si una contraseña a es o a sido comprometida
    @Bean
    public CompromisedPasswordChecker compromisedPasswordChecker(){
        return new HaveIBeenPwnedRestApiPasswordChecker();
    }
}
