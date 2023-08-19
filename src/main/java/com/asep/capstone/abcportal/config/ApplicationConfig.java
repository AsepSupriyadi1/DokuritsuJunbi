package com.asep.capstone.abcportal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class ApplicationConfig {

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://127.0.0.1:5500")); // Menentukan daftar origin yang diizinkan
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE")); // Menentukan metode yang diizinkan

        configuration.setAllowedHeaders(Arrays.asList("*"));  // Menentukan header yang diizinkan
        configuration.setAllowCredentials(true);    // Mengizinkan kredensial otentikasi

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // Mengaplikasikan konfigurasi ke semua URL

        return source;
    }


}
