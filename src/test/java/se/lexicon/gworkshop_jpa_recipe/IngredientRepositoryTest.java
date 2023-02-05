package se.lexicon.gworkshop_jpa_recipe;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.lexicon.gworkshop_jpa_recipe.entity.Ingredient;
import se.lexicon.gworkshop_jpa_recipe.repository.IngredientRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DataJpaTest
public class IngredientRepositoryTest {
    
    @Autowired
    IngredientRepository testObject;
    
    Ingredient createdIngredient;
    @BeforeEach
    public void setup(){

        Ingredient ingredient = new Ingredient("Salt");
        createdIngredient = testObject.save(ingredient);
        assertNotNull(createdIngredient);

    }
    
    @Test
    public void test_findById(){
        Optional<Ingredient> ingredientOptional = testObject.findById(createdIngredient.getId());
        assertTrue(ingredientOptional.isPresent());
        Ingredient actualData = ingredientOptional.get();
        Ingredient expectedData = createdIngredient;
        assertEquals(expectedData, actualData);
    }
    
    @Test
    public void test_remove(){
        Optional<Ingredient> ingredientOptional = testObject.findById(createdIngredient.getId());
        assertTrue(ingredientOptional.isPresent());

        Ingredient test = ingredientOptional.get();
        testObject.delete(test);
        assertFalse(testObject.findByIngredientName("Salt").isPresent());
    }

    @Test
    public void test_add(){
        Ingredient test = new Ingredient("Ingredient");
        testObject.save(test);

        Optional<Ingredient> ingredientData = testObject.findByIngredientName("Ingredient");
        assertTrue(ingredientData.isPresent());

    }


    @Test
    public void testFindByIngredient(){
        Optional<Ingredient> ingredientOptional = testObject.findByIngredientName("Salt");
        assertTrue(ingredientOptional.isPresent());
        Ingredient actualData = ingredientOptional.get();
        Ingredient expectedData = createdIngredient;
        assertEquals(expectedData,actualData);
    }
    @Test
    public void testFindAllByIngredientNameContainsIgnoreCase(){
        List<Ingredient> actualData = testObject.findAllByIngredientNameContainsIgnoreCase("salt");

        List<Ingredient> expectedData = new ArrayList<>();
        expectedData.add(createdIngredient);

        assertEquals(expectedData, actualData);
    }

}
