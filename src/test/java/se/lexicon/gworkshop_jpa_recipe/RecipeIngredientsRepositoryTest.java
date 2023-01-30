package se.lexicon.gworkshop_jpa_recipe;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.lexicon.gworkshop_jpa_recipe.entity.Ingredient;
import se.lexicon.gworkshop_jpa_recipe.entity.Measurement;
import se.lexicon.gworkshop_jpa_recipe.entity.RecipeIngredient;
import se.lexicon.gworkshop_jpa_recipe.repository.RecipeIngredientsRepository;

import java.util.Optional;

@DataJpaTest
public class RecipeIngredientsRepositoryTest {
    
    @Autowired
    RecipeIngredientsRepository testObject;
    
    RecipeIngredient createdRecipeIngredient;
    
    @BeforeEach
    public void setup(){
        Ingredient ingredient = new Ingredient("Flour");
        RecipeIngredient recipeIngredient = new RecipeIngredient(ingredient, 2, Measurement.DL);
        createdRecipeIngredient = testObject.save(recipeIngredient);
        assertNotNull(createdRecipeIngredient);
    }
    
    @Test
    public void test_findById(){
        Optional<RecipeIngredient> recipeIngredientOptional = testObject.findById(createdRecipeIngredient.getId());
        assertTrue(recipeIngredientOptional.isPresent());
        RecipeIngredient actualData = recipeIngredientOptional.get();
        RecipeIngredient expectedData = createdRecipeIngredient;
        assertEquals(expectedData, actualData);
    }
    
    @Test
    public void testRemove(){
        Optional<RecipeIngredient> recipeIngredientOptional = testObject.findById(createdRecipeIngredient.getId());
        assertTrue(recipeIngredientOptional.isPresent());
        testObject.delete(recipeIngredientOptional.get());
    }
    
    
}
