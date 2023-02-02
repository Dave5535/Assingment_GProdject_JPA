package se.lexicon.gworkshop_jpa_recipe.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import se.lexicon.gworkshop_jpa_recipe.entity.Recipe;
import se.lexicon.gworkshop_jpa_recipe.entity.RecipeCategory;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Integer> {
    
    Optional<Recipe> findByRecipeNameContainsIgnoreCase(String recipeName);
    
    List<Recipe> findAllByRecipeNameContainsIgnoreCase(String recipeName);

    Set<Recipe> findAllByCategoriesIsContaining(RecipeCategory category);

    // todo select *  from Recipe where Recipe.category = List of categories
   @Query("select r from Recipe r where r.categories IN :rc")
    Set<Recipe> findAllByCategoriesIgnoreCase(@Param("rc") Collection<String> recipeName);


}
