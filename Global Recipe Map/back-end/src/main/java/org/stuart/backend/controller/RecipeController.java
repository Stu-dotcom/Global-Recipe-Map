package org.stuart.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.stuart.backend.model.services.RecipeService;
import org.stuart.backend.model.dto.RecipeDTO;

import java.util.List;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    // Create a new recipe
    @PostMapping
    public ResponseEntity<RecipeDTO> createRecipe(@RequestBody RecipeDTO recipe) {
        RecipeDTO savedRecipe = recipeService.saveRecipe(recipe);
        return new ResponseEntity<>(savedRecipe, HttpStatus.CREATED);
    }

    // Get a recipe by ID
    @GetMapping("/{id}")
    public ResponseEntity<RecipeDTO> getRecipeById(@PathVariable Long id) {
        RecipeDTO recipe = recipeService.getRecipeById(id);
        return recipe != null ? ResponseEntity.ok(recipe) : ResponseEntity.notFound().build();
    }

    // Get all recipes
    @GetMapping
    public ResponseEntity<List<RecipeDTO>> getAllRecipes() {
        Iterable<RecipeDTO> recipes = recipeService.getAllRecipes();
        //Convert iterable to list for response
        return ResponseEntity.ok(StreamSupport.stream(recipes.spliterator(), false).toList());
    }

    // Update an existing recipe by ID
    @PutMapping("/{id}")
    public ResponseEntity<RecipeDTO> updateRecipe(@PathVariable Long id, @RequestBody RecipeDTO recipeDetails) {
        RecipeDTO updatedRecipe = recipeService.updateRecipe(id, recipeDetails);
        return updatedRecipe != null ? ResponseEntity.ok(updatedRecipe) : ResponseEntity.notFound().build();
    }

    // Delete a recipe by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable Long id) {
        boolean deleted = recipeService.deleteRecipe(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}

