package se.lexicon.gworkshop_jpa_recipe.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.lexicon.gworkshop_jpa_recipe.entity.RecipeIngredient;

@Repository
public interface RecipeIngredientsRepository extends CrudRepository<RecipeIngredient, String> {

}
