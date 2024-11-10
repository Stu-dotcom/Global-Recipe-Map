package org.stuart.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RecipeApplication {

	public static void main(String[] args) {
		//test h2 database
		SpringApplication.run(RecipeApplication.class, args);
	}

}
