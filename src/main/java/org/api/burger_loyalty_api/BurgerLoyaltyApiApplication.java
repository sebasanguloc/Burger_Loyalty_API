package org.api.burger_loyalty_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity(debug = true)
public class BurgerLoyaltyApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BurgerLoyaltyApiApplication.class, args);
    }

}
