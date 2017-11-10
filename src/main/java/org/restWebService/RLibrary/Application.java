package org.restWebService.RLibrary;

import org.restWebService.RLibrary.util.Constantes;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
            	registry.addMapping("/book/findAllByTitleAsc").allowedOrigins(Constantes.URL_CLIENT);
            	registry.addMapping("/book/findById/*").allowedOrigins(Constantes.URL_CLIENT);
            	registry.addMapping("/book/save").allowedOrigins(Constantes.URL_CLIENT);
            	registry.addMapping("/book/delete/*").allowedOrigins(Constantes.URL_CLIENT);
            	registry.addMapping("/book/uploadImage/*").allowedOrigins(Constantes.URL_CLIENT);
            	registry.addMapping("/bookType/findAllByDescriptionAsc").allowedOrigins(Constantes.URL_CLIENT);
            	registry.addMapping("/bookType/save").allowedOrigins(Constantes.URL_CLIENT);
            	registry.addMapping("/bookType/delete/*").allowedOrigins(Constantes.URL_CLIENT);
            	registry.addMapping("/bookFile/save").allowedOrigins(Constantes.URL_CLIENT);
            	registry.addMapping("/bookFile/findByIdBook/*").allowedOrigins(Constantes.URL_CLIENT);
            	registry.addMapping("/bookFile/delete/*").allowedOrigins(Constantes.URL_CLIENT);
            	registry.addMapping("/bookFile/uploadFile/*/*").allowedOrigins(Constantes.URL_CLIENT);
            	registry.addMapping("/bookUserPending/save").allowedOrigins(Constantes.URL_CLIENT);
            	registry.addMapping("/bookUserPending/delete/*").allowedOrigins(Constantes.URL_CLIENT);
            	registry.addMapping("/bookUserReaded/save").allowedOrigins(Constantes.URL_CLIENT);
            	registry.addMapping("/bookUserReaded/delete/*").allowedOrigins(Constantes.URL_CLIENT);
            	registry.addMapping("/rUser/findAllOrderByAliasAsc").allowedOrigins(Constantes.URL_CLIENT);
            	registry.addMapping("/rUser/save").allowedOrigins(Constantes.URL_CLIENT);
            	registry.addMapping("/rUser/delete/*").allowedOrigins(Constantes.URL_CLIENT);
            }
        };
    }
    
}
