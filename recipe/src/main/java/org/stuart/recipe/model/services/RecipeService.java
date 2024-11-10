package org.stuart.recipe.model.services;

import org.stuart.recipe.model.dto.RecipeDTO;
import org.stuart.recipe.model.entities.Recipe;

public interface RecipeService {
    Recipe saveRecipe(RecipeDTO recipe);
    Recipe getRecipeById(Long id);
    Iterable<Recipe> getAllRecipes();
    Recipe updateRecipe(Long id, Recipe recipeDetails);
    boolean deleteRecipe(Long id);
}

