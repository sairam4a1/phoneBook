package com.assignment.phonebookservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {


    /**
     * Using for access different applications.
     *
     * @param corsRegistry
     */
    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {
        final CorsRegistration corsRegistration = corsRegistry.addMapping("/**");
        corsRegistration.allowedOrigins("*");
        corsRegistration.allowedMethods("*");
        corsRegistration.allowedHeaders("*");
    }
}