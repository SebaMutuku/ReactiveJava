package com.learning.reactivejava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class ReactiveJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactiveJavaApplication.class, args);
    }

}
