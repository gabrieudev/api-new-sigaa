package br.edu.ifs.apinewsigaa.config.swagger;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI newSigaaOpenAPI(){
        return new OpenAPI()
                .info(new Info().title("New Sigaa")
                        .description("Projeto de API para um novo SIGAA, desenvolvido durando a disciplina de Programação 1")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("Repositório GitHub")
                        .url("https://github.com/gabrieudev/api-new-sigaa/"));
    }
}
