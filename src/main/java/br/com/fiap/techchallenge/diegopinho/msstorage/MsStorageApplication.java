package br.com.fiap.techchallenge.diegopinho.msstorage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsStorageApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsStorageApplication.class, args);
	}

}
