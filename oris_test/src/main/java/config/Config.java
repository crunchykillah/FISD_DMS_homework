package config;


import model.Art;
import model.Author;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan
public class Config {

    @Bean
    public Art art() {
        return new Art("Mona Lisa", author());
    }

    @Bean
    @Scope("prototype")
    public Author author() {
        return new Author("Леонардо", "Да винчи","Италия");
    }
}
