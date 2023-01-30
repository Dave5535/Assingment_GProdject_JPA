package se.lexicon.gworkshop_jpa_recipe.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.gworkshop_jpa_recipe.entity.Ingredient;

import java.util.List;
import java.util.Optional;

public interface IngredientRepository extends CrudRepository<Ingredient, Integer> {
    
    Optional<Ingredient> findByIngredientName(String ingredientName);
    
    List<Ingredient> findAllByIngredientNameContainsIgnoreCase(String ingredientName);

}
