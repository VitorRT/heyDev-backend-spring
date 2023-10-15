package br.com.api.heydev.configuration.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI configure() {
        Contact contact = new Contact();

        contact.setEmail("vitu.barberino@gmail.com");
        contact.setName("Victor Sousa Barberino");
        contact.setUrl("https://github.com/VitorRT");

        Info info = new Info()
                .title("Hey Dev Rest API")
                .version("1.0")
                .description("Monolithic that makes available all the services of the Hey Dev social network.")
                .contact(contact);

        return new OpenAPI().info(info);
    }
}
