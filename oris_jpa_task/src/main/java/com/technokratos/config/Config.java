package com.technokratos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Properties;

@Configuration
@ComponentScan("com.technokratos")
public class Config {

    @Bean
    public EntityManagerFactory getEntityManagerFactory() {
        return Persistence.createEntityManagerFactory("lab2_6");
    }

}