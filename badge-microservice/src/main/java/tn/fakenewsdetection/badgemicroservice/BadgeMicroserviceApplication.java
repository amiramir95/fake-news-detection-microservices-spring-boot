package tn.fakenewsdetection.badgemicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BadgeMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BadgeMicroserviceApplication.class, args);
	}

}
