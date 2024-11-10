package org.stuart.backend.model.services;

import org.stuart.backend.model.dto.RecipeDTO;

public interface RecipeService {
    RecipeDTO saveRecipe(RecipeDTO recipe);
    RecipeDTO getRecipeById(Long id);
    Iterable<RecipeDTO> getAllRecipes();
    RecipeDTO updateRecipe(Long id, RecipeDTO recipeDetails);
    boolean deleteRecipe(Long id);
}

