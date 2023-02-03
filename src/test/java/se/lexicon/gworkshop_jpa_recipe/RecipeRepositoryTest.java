package se.lexicon.gworkshop_jpa_recipe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.lexicon.gworkshop_jpa_recipe.entity.*;
import se.lexicon.gworkshop_jpa_recipe.repository.RecipeCategoryRepository;
import se.lexicon.gworkshop_jpa_recipe.repository.RecipeInstructionRepository;
import se.lexicon.gworkshop_jpa_recipe.repository.RecipeRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
public class RecipeRepositoryTest {
    
    @Autowired
    RecipeRepository testObject;
    @Autowired
    RecipeInstructionRepository instruction;
    @Autowired
    RecipeCategoryRepository categoryRepository;
    
    Recipe createdRecipe;
    RecipeInstruction createdInstruction;
    RecipeCategory createdCategory;
    RecipeIngredient createdIngredient;
    
    @BeforeEach
    public void setup(){
        Ingredient ingredient = new Ingredient("Flour");
        RecipeIngredient recipeIngredient = new RecipeIngredient(ingredient, 2, Measurement.DL);
        List<RecipeIngredient> ingredientList = new ArrayList<>();
        ingredientList.add(recipeIngredient);
        RecipeInstruction recipeInstruction = new RecipeInstruction("testInstruction");
        RecipeCategory recipeCategory = new RecipeCategory("categoryTest");
        Set<RecipeCategory> recipeCategories = new HashSet<>();
       createdCategory = recipeCategory;
        recipeCategories.add(recipeCategory);
        Recipe recipe = new Recipe("Cake", ingredientList,recipeInstruction,recipeCategories);
        createdRecipe = testObject.save(recipe);
        assertNotNull(createdRecipe);
    }
    
    @Test
    public void test_findById(){
        Optional<Recipe> recipeOptional = testObject.findById(createdRecipe.getId());
        assertTrue(recipeOptional.isPresent());
        Recipe actualData = recipeOptional.get();
        Recipe expectedData = createdRecipe;
        assertEquals(expectedData, actualData);
    }
    
    @Test
    public void test_remove(){
        Optional<Recipe> recipeOptional = testObject.findById(createdRecipe.getId());
        assertTrue(recipeOptional.isPresent());
        testObject.delete(recipeOptional.get());
    }
    
    @Test
    public void test_addIngredient(){
        createdRecipe.addRecipeIngredient(createdIngredient);
    }
    
    @Test
    public void test_addCategory(){
        createdRecipe.addRecipeCategory(createdCategory);
    }

    @Test
    public void TestFindAllByRecipeName() {

        List<Recipe> actualData = testObject.findAllByRecipeNameContainsIgnoreCase("Cake");

        List<Recipe> expectedData = new ArrayList<>();
        expectedData.add(createdRecipe);

        assertEquals(expectedData, actualData);
    }


    @Test
    public void TestFindByCategory() {
        Set<Recipe> actualData = testObject.findAllByCategoriesIsContaining(createdCategory);
        Set<Recipe> expectedData = new HashSet<>();
        expectedData.add(createdRecipe);
        assertEquals(expectedData, actualData);
    }
    @Test
    public void TestFindByCategories() {

        //todo fix this ... Search for all recipes that match one or more categories.
        
        System.out.println("#########################");
        
        Collection<String> categories = new ArrayList<>();
        categories.add("categoryTest");
        
        
        Set<Recipe> actualData = testObject.findAllByCategoriesIgnoreCase(categories); // string is null
        System.out.println("#########################");

        Recipe expectedData = createdRecipe;

        assertEquals(expectedData, actualData);

}
}
