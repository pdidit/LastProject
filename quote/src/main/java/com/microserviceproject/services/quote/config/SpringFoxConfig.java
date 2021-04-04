package com.microserviceproject.services.quote.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SpringFoxConfig {
    public static final Contact CUSTOM_CONTACT = new Contact("Padraic Meehan", "www.ait.ie", "padraic.meehan@gmail.com");
    public static final ApiInfo CUSTOM_API_INFO = new ApiInfoBuilder().title("Padraic Custom Quote API")
            .description("customer quote desciption API").version("1.0").contact(CUSTOM_CONTACT).build();
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select().apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(CUSTOM_API_INFO);
    }
}