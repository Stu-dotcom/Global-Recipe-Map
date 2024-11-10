package org.stuart.recipe.model.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.stuart.recipe.model.dto.RecipeDTO;
import org.stuart.recipe.model.entities.Recipe;
import org.stuart.recipe.model.entities.User;
import org.stuart.recipe.model.repositories.RecipeRepository;
import org.stuart.recipe.model.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private UserRepository userRepository;

    // Save a new recipe
    @Override
    public Recipe saveRecipe(RecipeDTO recipeDTO) {
        Optional<User> userOptional = userRepository.findById(recipeDTO.getUserId());

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Recipe recipe = new Recipe();
            recipe.setName(recipeDTO.getName());
            recipe.setDescription(recipeDTO.getDescription());
            recipe.setIngredients(recipeDTO.getIngredients());
            recipe.setLocation(recipeDTO.getLocation());
            recipe.setSubmittedBy(user);

            return recipeRepository.save(recipe);
        } else {
            throw new IllegalArgumentException("User with ID " + recipeDTO.getUserId() + " not found");
        }
    }


    // Retrieve a recipe by its ID
    @Override
    public Recipe getRecipeById(Long id) {
        Optional<Recipe> recipe = recipeRepository.findById(id);
        return recipe.orElse(null);
    }

    // Get a list of all recipes
    @Override
    public Iterable<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    // Update an existing recipe by ID
    @Override
    public Recipe updateRecipe(Long id, Recipe recipeDetails) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(id);

        if (recipeOptional.isPresent()) {
            Recipe recipe = recipeOptional.get();
            recipe.setName(recipeDetails.getName());
            recipe.setDescription(recipeDetails.getDescription());
            recipe.setIngredients(recipeDetails.getIngredients());
            recipe.setLocation(recipeDetails.getLocation());
            return recipeRepository.save(recipe);
        } else {
            //TODO: throw exception for better error handling
            return null;
        }
    }

    // Delete a recipe by ID
    @Override
    public boolean deleteRecipe(Long id) {
        if (recipeRepository.existsById(id)) {
            recipeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
