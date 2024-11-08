package org.stuart.recipe.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.stuart.recipe.entities.Recipe;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
