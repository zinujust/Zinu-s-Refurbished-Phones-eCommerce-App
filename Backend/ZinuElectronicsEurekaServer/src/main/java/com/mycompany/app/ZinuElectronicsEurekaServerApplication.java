package com.mycompany.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class ZinuElectronicsEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZinuElectronicsEurekaServerApplication.class, args);
	}

}
