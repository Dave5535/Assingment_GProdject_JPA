package se.lexicon.gworkshop_jpa_recipe.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.lexicon.gworkshop_jpa_recipe.entity.RecipeInstruction;


@Repository
public interface RecipeInstructionRepository extends CrudRepository<RecipeInstruction, Integer> {

}
