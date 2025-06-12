package br.com.mascenadev.crud.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuração web global para a aplicação.
 * Esta classe implementa {@link WebMvcConfigurer} para customizar a configuração padrão do Spring MVC,
 * especificamente para configurar o CORS (Compartilhamento de Recursos de Origem Cruzada).
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * Configura o CORS (Cross-Origin Resource Sharing) para a aplicação.
     * Este método define as regras para quais origens são permitidas acessar os recursos
     * e quais métodos HTTP e cabeçalhos são permitidos para caminhos específicos.
     *
     * @param registry O {@link CorsRegistry} ao qual os mapeamentos CORS são adicionados.
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/todos/**")
                .allowedOrigins("http://localhost:4200")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}