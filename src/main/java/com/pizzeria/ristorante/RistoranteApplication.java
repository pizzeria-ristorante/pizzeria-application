package com.pizzeria.ristorante;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class RistoranteApplication {

    public static void main(String[] args) {
        SpringApplication.run(RistoranteApplication.class, args);
    }

}
