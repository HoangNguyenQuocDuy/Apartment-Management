package hnqd.project.ApartmentManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableJpaRepositories
@EnableWebSecurity
@EnableMongoRepositories({"hnqd.project.ApartmentManagement.repository"})
public class ApartmentManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApartmentManagementApplication.class, args);
	}

}
