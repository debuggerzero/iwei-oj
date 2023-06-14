package com.zero.acskybackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author ZERO
 */
@SpringBootApplication
@EnableTransactionManagement
public class AcskyBackEndApplication {

    public static void main(String[] args) {
        SpringApplication.run(AcskyBackEndApplication.class, args);
    }

}
