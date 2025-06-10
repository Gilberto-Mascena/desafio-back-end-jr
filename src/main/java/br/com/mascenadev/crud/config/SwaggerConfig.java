package br.com.mascenadev.crud.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Value("${todo-api.api.version}")
    private String apiVersion;

    @Bean
    public OpenAPI customApi() {
        return new OpenAPI().info(new Info()
                .title("Todo API")
                .version(apiVersion)
                .description("Documentation of Todo API")
                .license(apiLicense()));
    }

    private License apiLicense() {
        return new License()
                .name("MIT License")
                .url("https://github.com/Gilberto-Mascena/desafio-back-end-jr/blob/main/LICENSE.md");
    }
}
