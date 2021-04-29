package br.com.prcompany.msscbrewerygateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MsscBreweryGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsscBreweryGatewayApplication.class, args);
    }

}
