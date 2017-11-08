package org.restWebService.RLibrary;

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
            	registry.addMapping("/book/findAllByTitleAsc");
            	registry.addMapping("/book/findById/*");
            	registry.addMapping("/book/save");
            	registry.addMapping("/book/delete/*");
            	registry.addMapping("/book/uploadImage/*");
            	registry.addMapping("/bookType/findAllByDescriptionAsc");
            	registry.addMapping("/bookType/save");
            	registry.addMapping("/bookType/delete/*");
            	registry.addMapping("/bookFile/save");
            	registry.addMapping("/bookFile/findByIdBook/*");
            	registry.addMapping("/bookFile/delete/*");
            	registry.addMapping("/bookFile/uploadFile/*/*");
            	registry.addMapping("/bookUserPending/save");
            	registry.addMapping("/bookUserPending/delete/*");
            	registry.addMapping("/bookUserReaded/save");
            	registry.addMapping("/bookUserReaded/delete/*");
            	registry.addMapping("/rUser/findAllOrderByAliasAsc");
            	registry.addMapping("/rUser/save");
            	registry.addMapping("/rUser/delete/*");
            }
        };
    }
    
}
