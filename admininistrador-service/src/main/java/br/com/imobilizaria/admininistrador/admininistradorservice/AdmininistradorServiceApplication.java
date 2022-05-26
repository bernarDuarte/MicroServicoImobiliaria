package br.com.imobilizaria.admininistrador.admininistradorservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AdmininistradorServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdmininistradorServiceApplication.class, args);
	}

}
