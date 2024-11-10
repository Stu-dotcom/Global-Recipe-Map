package org.stuart.recipe.model.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.stuart.recipe.model.entities.Recipe;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
