package org.stuart.backend.model.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.stuart.backend.model.entities.Recipe;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
