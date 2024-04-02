package config;


import lombok.extern.slf4j.Slf4j;
import model.Basket;
import model.Client;
import model.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Configuration
@ComponentScan
public class BeanRegister {

    @Bean
    public Product milk() {
        return new Product("Молоко",70.0);
    }

    @Bean
    public Product bread() {
        return new Product("Хлеб",30.0);
    }

    @Bean
    public Product cheese() {
        return new Product("сыр",150.0);
    }

    @Bean
    public Basket basket() {
        List<Product> productList = new ArrayList<>();
        productList.add(milk());
        productList.add(cheese());
        productList.add(bread());
        return new Basket(productList);
    }

    @Bean
    public Client client() {
        return new Client(UUID.randomUUID(), "user", "pass", basket());
    }
}
