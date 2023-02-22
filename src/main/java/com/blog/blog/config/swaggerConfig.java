package com.blog.blog.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
@Configuration
public class swaggerConfig {
    
    @Bean
      public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(getInfo()).select().apis(RequestHandlerSelectors.any()).paths(PathSelectors.any()).build();
    }

    private ApiInfo getInfo() {

        return new ApiInfo("blogging appilication:backend Course", "This project is developed by jaid sheikh ","1.0", "Terms of service",new springfox.documentation.service.Contact("jaid", "https://jaidscodingpoint.com", "jaidsheikh7@gmail.com") , "License of APIS", "API license URL", Collections.emptyList()) ;
    }


}
