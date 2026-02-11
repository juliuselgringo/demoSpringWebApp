package fr.juliuselgringo.demospringwebapp.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix="fr.juliuselgringo.demospringwebapp")
@Data
public class CustomProperty {

    private String apiUrl;
}
