package org.stuart.backend.data;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.stuart.backend.model.entities.User;
import org.stuart.backend.model.entities.Recipe;
import org.stuart.backend.model.repositories.UserRepository;
import org.stuart.backend.model.repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RecipeRepository recipeRepository;

    @Autowired
    public DataInitializer(UserRepository userRepository, RecipeRepository recipeRepository) {
        this.userRepository = userRepository;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Sample users
        User user1 = userRepository.save(new User("john_doe", "password123", "john@example.com"));
        User user2 = userRepository.save(new User("jane_doe", "password456", "jane@example.com"));

        user1 = userRepository.findById(user1.getId()).orElseThrow();
        user2 = userRepository.findById(user2.getId()).orElseThrow();

        // Sample recipes with lists of ingredients
        List<String> pastaIngredients = Arrays.asList("Pasta", "Tomato Sauce", "Ground Beef");
        List<String> curryIngredients = Arrays.asList("Chicken", "Curry Powder", "Coconut Milk", "Rice");
        List<String> cheeseBurgerIngredients = Arrays.asList("Bun", "Meat Patty", "Cheese Slice");

        //Glasgow long and lat
        Double glasgowLat = 55.8617;
        Double glasgowLong = 4.2583;
        //Edinburgh long and lat
        Double edinburghLat = 55.9533;
        Double edinburghLong = 3.1883;

        Recipe recipe1 = new Recipe("Pasta Bolognese", "Classic Italian pasta dish", pastaIngredients, glasgowLat, glasgowLong, user1);
        Recipe recipe2 = new Recipe("Chicken Curry", "Spicy and delicious", curryIngredients, glasgowLat, glasgowLong, user2);
        Recipe recipe3 = new Recipe("Cheese Burger", "Basic cheeseburger", cheeseBurgerIngredients, edinburghLat, edinburghLong, user1);


        recipeRepository.saveAll(Arrays.asList(recipe1, recipe2, recipe3));
    }
}
