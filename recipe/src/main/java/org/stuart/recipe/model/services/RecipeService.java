package org.stuart.recipe.model.services;

import org.stuart.recipe.model.dto.RecipeDTO;
import org.stuart.recipe.model.entities.Recipe;

public interface RecipeService {
    RecipeDTO saveRecipe(RecipeDTO recipe);
    RecipeDTO getRecipeById(Long id);
    Iterable<RecipeDTO> getAllRecipes();
    RecipeDTO updateRecipe(Long id, RecipeDTO recipeDetails);
    boolean deleteRecipe(Long id);
}

