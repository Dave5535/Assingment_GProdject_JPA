package se.lexicon.gworkshop_jpa_recipe;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.lexicon.gworkshop_jpa_recipe.entity.RecipeCategory;
import se.lexicon.gworkshop_jpa_recipe.repository.RecipeCategoryRepository;

import java.util.Optional;

@DataJpaTest
public class RecipeCategoryRepositoryTest {
    
    @Autowired
    RecipeCategoryRepository testObject;
    
    RecipeCategory createdRecipeCategory;
    
    @BeforeEach
    public void setup(){
        RecipeCategory recipeCategory = new RecipeCategory("Italian");
        createdRecipeCategory = testObject.save(recipeCategory);
        assertNotNull(createdRecipeCategory);
    }
    
    @Test
    public void test_findById(){
        Optional<RecipeCategory> recipeCategoryOptional = testObject.findById(createdRecipeCategory.getId());
        assertTrue(recipeCategoryOptional.isPresent());
        RecipeCategory actualData = recipeCategoryOptional.get();
        RecipeCategory expectedData = createdRecipeCategory;
        assertEquals(expectedData, actualData);
    }
    
    @Test
    public void test_remove(){
        Optional<RecipeCategory> recipeCategoryOptional = testObject.findById(createdRecipeCategory.getId());
        assertTrue(recipeCategoryOptional.isPresent());
        testObject.delete(recipeCategoryOptional.get());
    }
    
}
