package com.oxigen.navesespaciales;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class NavesEspacialesApplication {

    public static void main(String[] args) {
        SpringApplication.run(NavesEspacialesApplication.class, args);
    }

}
