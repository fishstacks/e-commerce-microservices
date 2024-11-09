package com.neonspecs.inventoryms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class InventorymsApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventorymsApplication.class, args);
	}

}
