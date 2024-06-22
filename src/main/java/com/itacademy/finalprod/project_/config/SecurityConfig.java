package com.itacademy.finalprod.project_.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.reactive.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAutenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;


   @Bean
   public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       http
               .csrf().disable()
               .authorizeHttpRequests()
               // Разрешаем доступ к Swagger UI без авторизации
               .requestMatchers(swaggerRequestMatcher()).permitAll()
               .requestMatchers(new AntPathRequestMatcher("/**")).permitAll()
               // Все остальные запросы требуют аутентификации
               .anyRequest().authenticated()
               .and()
               .authenticationProvider(authenticationProvider)
               .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
       return http.build();
   }
   private RequestMatcher swaggerRequestMatcher() {
       // Здесь указываем пути, по которым расположен Swagger UI
       // Например, если Swagger UI доступен по "/swagger-ui/**" и "/swagger-ui.html"
       return new OrRequestMatcher(
               new AntPathRequestMatcher("/swagger-editor/**"),
               new AntPathRequestMatcher("/swagger-ui.html"),
               new AntPathRequestMatcher("/v3/api-docs/**")
       );
   }
}
