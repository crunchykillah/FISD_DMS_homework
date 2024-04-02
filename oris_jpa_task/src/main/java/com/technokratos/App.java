package com.technokratos;

import com.technokratos.config.Config;
import com.technokratos.model.Client;
import com.technokratos.model.Product;
import com.technokratos.repository.ClientRepository;
import com.technokratos.repository.ProductRepository;
import com.technokratos.repository.UserRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(Config.class);

        ClientRepository clientRepository = context.getBean(ClientRepository.class);
        ProductRepository productRepository = context.getBean(ProductRepository.class);
        UserRepository userRepository = context.getBean(UserRepository.class);

        Product product = new Product();
        product.setName("Продукт 1");
        product.setWeight(10);
        productRepository.save(product);

        List<Product> productList = new ArrayList<>();

        productList.add(product);

        Client client = new Client();
        client.setUsername("Клиент 1");
        client.setPassword("пароль");
        client.setProducts(productList);
        clientRepository.save(client);
        System.out.println(client.getId());

        client.setUsername(client.getUsername() + " 1");
        clientRepository.save(client);

    }
}
