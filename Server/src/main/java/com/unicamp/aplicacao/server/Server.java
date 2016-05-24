package com.unicamp.aplicacao.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author sabrina on 16/05/16.
 */
@Configuration
@EnableAutoConfiguration
@EnableTransactionManagement
@ComponentScan("com.meleva")
public class Server {

    public static void main(String[] args) {
        SpringApplication.run(Server.class, args);
    }

}
