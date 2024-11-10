package org.stuart.backend.model.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.stuart.backend.model.entities.User;
import org.stuart.backend.model.dto.RecipeDTO;
import org.stuart.backend.model.entities.Recipe;
import org.stuart.backend.model.repositories.RecipeRepository;
import org.stuart.backend.model.repositories.UserRepository;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private UserRepository userRepository;

    // Save a new recipe
    @Override
    public RecipeDTO saveRecipe(RecipeDTO recipeDTO) {
        Optional<User> userOptional = userRepository.findById(recipeDTO.getUserId());

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Recipe recipe = new Recipe();
            recipe.setName(recipeDTO.getName());
            recipe.setDescription(recipeDTO.getDescription());
            recipe.setIngredients(recipeDTO.getIngredients());
            recipe.setLocation(recipeDTO.getLocation());
            recipe.setSubmittedBy(user);

            return convertToRecipeDTO(recipeRepository.save(recipe));
        } else {
            throw new IllegalArgumentException("User with ID " + recipeDTO.getUserId() + " not found");
        }
    }

    // Retrieve a recipe by its ID
    @Override
    public RecipeDTO getRecipeById(Long id) {
        Optional<Recipe> recipe = recipeRepository.findById(id);
        RecipeDTO recipeDTO = null;
        if (recipe.isPresent()) {
            recipeDTO = convertToRecipeDTO(recipe.get());
        }
        return recipeDTO;
    }

    // Get a list of all recipes as DTOs
    @Override
    public Iterable<RecipeDTO> getAllRecipes() {
        return StreamSupport.stream(recipeRepository.findAll().spliterator(), false)
                .map(this::convertToRecipeDTO)
                .collect(Collectors.toList());
    }

    // Update an existing recipe by ID
    @Override
    public RecipeDTO updateRecipe(Long id, RecipeDTO recipeDTO) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(id);

        if (recipeOptional.isPresent()) {
            Recipe recipe = recipeOptional.get();
            Optional<User> userOptional = userRepository.findById(recipeDTO.getUserId());

            if (userOptional.isPresent()) {
                User user = userOptional.get();
                recipe.setName(recipeDTO.getName());
                recipe.setDescription(recipeDTO.getDescription());
                recipe.setIngredients(recipeDTO.getIngredients());
                recipe.setLocation(recipeDTO.getLocation());
                recipe.setSubmittedBy(user);

                return convertToRecipeDTO(recipeRepository.save(recipe));
            } else {
                throw new IllegalArgumentException("User with ID " + recipeDTO.getUserId() + " not found");
            }
        } else {
            throw new IllegalArgumentException("Recipe with ID " + id + " not found");
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

    // Convert Recipe object to RecipeDTO
    private RecipeDTO convertToRecipeDTO(Recipe recipe) {
        RecipeDTO dto = new RecipeDTO();
        dto.setName(recipe.getName());
        dto.setDescription(recipe.getDescription());
        dto.setIngredients(recipe.getIngredients());
        dto.setLocation(recipe.getLocation());
        dto.setUserId(recipe.getSubmittedBy().getId());
        return dto;
    }
}
