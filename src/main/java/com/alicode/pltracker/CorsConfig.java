package com.alicode.pltracker;
import java.util.List;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilterRegistration() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("*")); // exact origin required if allowCredentials=true
        config.setAllowedMethods(List.of("POST", "OPTIONS"));       // SOAP requests are POST; allow OPTIONS for preflight
        config.setAllowedHeaders(List.of("Content-Type", "Accept", "Authorization"));
        //config.setAllowCredentials(true);
        config.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // register for the soap path. If your MessageDispatcherServlet is mapped to /ws/*, use /ws/**
        source.registerCorsConfiguration("/ws/**", config);

        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }
}