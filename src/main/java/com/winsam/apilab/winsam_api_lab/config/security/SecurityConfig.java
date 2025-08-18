package com.winsam.apilab.winsam_api_lab.config.security;

import com.winsam.apilab.winsam_api_lab.config.security.jwt.TokenProcessFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

/**
 * ************************************************************************
 * File Name   : SecurityConfig
 * Author      : Dev-004
 * Date        : 2025-07-02
 * Description :
 * -------------------------- Modification Log ----------------------------
 * 버젼 :                  수정자 :
 * 날짜 :
 * 내용 :
 * ------------------------------------------------------------------------
 * 1.0 :  : 작성자 :
 * 내용 :
 * ************************************************************************
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .formLogin(form -> form.disable())
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(registry -> registry
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll() // OPTIONS 허용
                        .requestMatchers("/auth/**").permitAll()               // 필요 시 공개 API
                        .requestMatchers("/redis/**").permitAll()
                        .requestMatchers("/bbs/**").permitAll()
                        .requestMatchers("/bbs/uploads/**").permitAll()
                        .anyRequest().permitAll()                              // 나머지도 허용
                );
//                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
//                .cors(Customizer.withDefaults())  // CORS 활성화
//                .httpBasic(Customizer.withDefaults())
//                .formLogin(form -> form.disable())
//                .csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(registry -> registry.anyRequest().permitAll())
//                .authorizeHttpRequests(registry -> registry
//                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
//                                .anyRequest().permitAll()  // 모든 요청 인증 없이 허용
//                        .requestMatchers("/auth/**").permitAll() // 인증 없이 허용
//                        .requestMatchers("/redis/**").permitAll() // 인증 없이 허용
//                        .requestMatchers("/bbs/**").permitAll() // 인증 없이 허용
//                        .requestMatchers("/bbs/uploads/**").permitAll()
//                        .anyRequest().authenticated()            // 나머지는 인증 필요
//                );
//                .addFilterBefore(new TokenProcessFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(List.of(
                "http://localhost:5173",
                "http://192.168.219.106:5173",
                "http://blog.winsam.xyz",
                "https://blog.winsam.xyz"
        ));
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
//    @Bean
//    public CorsFilter corsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true);
//        config.addAllowedOrigin("http://localhost:5173");
//        config.addAllowedOrigin("http://192.168.219.106:5173");
//        config.addAllowedOrigin("http://blog.winsam.xyz");
//        config.addAllowedHeader("*");
//        config.addAllowedMethod("*");
//        source.registerCorsConfiguration("/**", config);
//        return new CorsFilter(source);
//    }

}