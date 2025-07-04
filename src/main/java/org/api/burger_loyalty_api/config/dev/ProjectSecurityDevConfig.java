package org.api.burger_loyalty_api.config.dev;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.api.burger_loyalty_api.exceptionHandling.CustomAccessDeniedHandler;
import org.api.burger_loyalty_api.exceptionHandling.CustomAuthenticationEntryPoint;
import org.api.burger_loyalty_api.filter.CsrfCookieFilter;
import org.api.burger_loyalty_api.filter.JWTTokenGeneratorFilter;
import org.api.burger_loyalty_api.filter.JWTTokenValidationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

import static org.springframework.security.config.Customizer.withDefaults;

@Slf4j
@Configuration
@Profile("dev")
public class ProjectSecurityDevConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        CsrfTokenRequestAttributeHandler csrfHandler = new CsrfTokenRequestAttributeHandler();

        http
                .sessionManagement(smc -> smc
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .csrf(csrfConfig -> csrfConfig
                        .ignoringRequestMatchers("/auth/register")
                        .csrfTokenRequestHandler(csrfHandler)
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .cors(corsConfig ->
                        corsConfig.configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration config = new CorsConfiguration();
                        config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
                        config.setAllowedMethods(Collections.singletonList("*"));
                        config.setAllowCredentials(true);
                        config.setAllowedHeaders(Arrays.asList("Authorization"));
                        config.setMaxAge(3600L);
                        return config;
                    }
                }))
                .sessionManagement(smc -> smc
                        .maximumSessions(1)
                        .maxSessionsPreventsLogin(false)
                )
                .requiresChannel((channel) -> channel
                        .anyRequest().requiresInsecure()
                )
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/auth/register").permitAll()
                        .requestMatchers("/clients/**").hasRole("ADMIN")
                        .requestMatchers("/client/**").hasRole("CLIENT")
                        .requestMatchers("/auth/Angus-Burgers-Loyalty").authenticated()
                )
                .httpBasic(hbc -> hbc
                        .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                )
                .exceptionHandling(hbc -> hbc
                        .accessDeniedHandler(new CustomAccessDeniedHandler())
                )
                .addFilterAfter(
                        new CsrfCookieFilter(),
                        BasicAuthenticationFilter.class
                )
                .addFilterAfter(
                        new JWTTokenGeneratorFilter(),
                        BasicAuthenticationFilter.class
                )
                .addFilterAfter(
                        new JWTTokenValidationFilter(),
                        BasicAuthenticationFilter.class
                );
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
