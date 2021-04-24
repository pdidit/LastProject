package com.microserviceproject.client.clientapplication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Config file for swagger ui.
 */
@Configuration
public class SpringFoxConfig {
    /**
     * constant variables for swagger information.
     */
    public static final Contact CUSTOM_CONTACT = new Contact("Padraic Meehan", "www.ait.ie", "padraic.meehan@gmail.com");
    public static final ApiInfo CUSTOM_API_INFO = new ApiInfoBuilder().title("Padraic Custom Quote API")
            .description("Customer Application API").version("1.0").contact(CUSTOM_CONTACT).build();
    /**
     * Bean need to create the swagger UI
     * @return Docker Object need to create a new Swagger UI
     */
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select().apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(CUSTOM_API_INFO);
    }
}
