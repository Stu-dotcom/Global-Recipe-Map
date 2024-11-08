package org.stuart.recipe;

import org.springframework.boot.ConfigurableBootstrapContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.stuart.recipe.entities.Recipe;
import org.stuart.recipe.entities.User;
import org.stuart.recipe.repositories.RecipeRepository;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EntityScan(basePackages = "org.stuart.recipe.entities")
@EnableJpaRepositories(basePackages = "org.stuart.recipe.repositories")
public class RecipeApplication {

	public static void main(String[] args) {
		//test h2 database
		ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(RecipeApplication.class, args);
		RecipeRepository recipeRepository = configurableApplicationContext.getBean(RecipeRepository.class);
		//User
		User testUser = new User("user", "pass", "user@gmail.com");
		//Recipe
		List<String> ingredients = new ArrayList<>();
		ingredients.add("Bun");
		ingredients.add("Meat Patty");
		ingredients.add("Cheese");
		Recipe recipe = new Recipe("Cheese Burger", "Basic Cheeseburger", ingredients, "Glasgow", testUser);
		//add to table
		recipeRepository.save(recipe);
	}

}
