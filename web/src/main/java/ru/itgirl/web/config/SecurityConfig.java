package ru.itgirl.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

     // настройка конфигурации для проверки работы CRUD-методов (максимальные права всем и на всё)
     @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http
                    .csrf(csrf -> csrf.disable())  // Отключаем CSRF
                    .authorizeHttpRequests(auth -> auth
                            .requestMatchers("/auth/register").permitAll()
                            .anyRequest().permitAll()); // Разрешаем доступ всем
         // .requestMatchers("/auth/login").permitAll() // это обязательно надо сделать будет для АУТЕНТИФИКАЦИИ, когда всё остальное поставим по ролям
          //        .anyRequest().authenticated() // остальные запросы требуют аутентификации (можно будет потом добавить)
            return http.build();
        }
    }
