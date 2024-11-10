package org.stuart.recipe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.stuart.recipe.model.entities.Recipe;
import org.stuart.recipe.model.entities.User;
import org.stuart.recipe.model.repositories.RecipeRepository;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class RecipeApplication {

	public static void main(String[] args) {
		//test h2 database
		SpringApplication.run(RecipeApplication.class, args);
	}

}
