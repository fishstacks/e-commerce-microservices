package com.neonspecs.productms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ProductmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductmsApplication.class, args);
	}

}
