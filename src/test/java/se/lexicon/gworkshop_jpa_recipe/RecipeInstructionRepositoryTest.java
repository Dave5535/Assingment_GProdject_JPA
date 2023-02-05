package se.lexicon.gworkshop_jpa_recipe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.lexicon.gworkshop_jpa_recipe.entity.RecipeInstruction;
import se.lexicon.gworkshop_jpa_recipe.repository.RecipeInstructionRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class RecipeInstructionRepositoryTest {
    
    @Autowired
    RecipeInstructionRepository testObject;
    
    RecipeInstruction createdRecipeInstruction;
    
    @BeforeEach
    public void setup(){
        RecipeInstruction recipeInstruction = new RecipeInstruction("Test Instruction");
        createdRecipeInstruction = testObject.save(recipeInstruction);
        assertNotNull(createdRecipeInstruction);
    }
    
    @Test
    public void test_findById(){
        Optional<RecipeInstruction> recipeInstructionOptional = testObject.findById(createdRecipeInstruction.getId());
        assertTrue(recipeInstructionOptional.isPresent());
        RecipeInstruction actualData = recipeInstructionOptional.get();
        RecipeInstruction expectedData = createdRecipeInstruction;
        assertEquals(expectedData, actualData);
    }
    
    @Test
    public void test_remove(){
        Optional<RecipeInstruction> recipeInstructionOptional = testObject.findById(createdRecipeInstruction.getId());
        assertTrue(recipeInstructionOptional.isPresent());
        testObject.delete(recipeInstructionOptional.get());
        Optional<RecipeInstruction> testData = testObject.findById(recipeInstructionOptional.get().getId());
        assertFalse(testData.isPresent());
    }
    @Test
    public void test_add(){
        RecipeInstruction recipeInstruction = new RecipeInstruction("Test");
        testObject.save(recipeInstruction);
        Optional<RecipeInstruction> testData = testObject.findById(recipeInstruction.getId());
        assertTrue(testData.isPresent());
    }
    
    
}
