package com.example.service_produit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws
//            Exception {
//        return http.authorizeHttpRequests( authorize -> authorize.anyRequest()
//                        .authenticated())
//                .oauth2ResourceServer( ouath2 ->
//                        ouath2.jwt(Customizer.withDefaults()) )
//                .build();
//    }
//}


//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws
//            Exception {
//        return http.authorizeHttpRequests( authorize -> authorize.anyRequest()
//                        .authenticated())
//                .oauth2ResourceServer( ouath2 ->
//                        ouath2.jwt(Customizer.withDefaults()) )
//                .build();
//    }
//}

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().permitAll()
                )
                .csrf(csrf -> csrf.disable())
                .oauth2ResourceServer(oauth2 -> oauth2.disable()); // ⚠ désactive JWT

        return http.build();
    }
}

