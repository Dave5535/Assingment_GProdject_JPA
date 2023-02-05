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


    @Query("select r from Recipe r left join r.categories c group by r having r.recipeName = :rn")
    Set<Recipe> findRecipesPresentInCategoriesIgnoreCase(@Param("rn") String recipeName);

    @Query("select r from Recipe r left join r.categories c group by r having r = :rn")
    Set<Recipe> findAllRecipesPresentInCategoriesIgnoreCase(@Param("rn") Recipe recipeName);
}
