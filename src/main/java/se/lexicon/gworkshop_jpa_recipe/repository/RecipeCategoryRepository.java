package se.lexicon.gworkshop_jpa_recipe.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.lexicon.gworkshop_jpa_recipe.entity.RecipeCategory;

@Repository
public interface RecipeCategoryRepository extends CrudRepository<RecipeCategory, Integer> {

}
