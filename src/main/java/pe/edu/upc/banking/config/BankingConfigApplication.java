package pe.edu.upc.banking.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class BankingConfigApplication {
	public static void main(String[] args) {
		SpringApplication.run(BankingConfigApplication.class, args);
	}
}