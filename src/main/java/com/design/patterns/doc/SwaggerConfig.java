package com.design.patterns.doc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI configureOpenAPI() {
        Contact contact = new Contact();
        contact.name("Cristian Mathias");
        contact.email("cristian11mathias@hotmail.com");
        contact.url("https://www.linkedin.com/in/cristian-mathias");

        Info info = new Info();
        info.title("Design Patterns com Java: Dos Clássicos (GoF) ao Spring Framework");
        info.description("Praticando com Spring Padrões de Projeto em uma API REST .");
        info.version("1.0.0");
        info.contact(contact);

        SecurityScheme securityScheme = new SecurityScheme()
                .name("JWT")
                .type(io.swagger.v3.oas.models.security.SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT");
        //habilitando o campo de validação do token
        return new OpenAPI()
                .components(new Components().addSecuritySchemes("bearerAuth", securityScheme))
                .info(info)
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"));
    }

}

