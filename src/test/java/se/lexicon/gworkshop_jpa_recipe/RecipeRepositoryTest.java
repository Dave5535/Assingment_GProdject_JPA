package se.lexicon.gworkshop_jpa_recipe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.lexicon.gworkshop_jpa_recipe.entity.*;
import se.lexicon.gworkshop_jpa_recipe.repository.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
public class RecipeRepositoryTest {

    @Autowired
    RecipeRepository testRecipe;
    @Autowired
    IngredientRepository testIngredient;
    @Autowired
    RecipeInstructionRepository testRecipeInstruction;
    @Autowired
    RecipeIngredientsRepository testRecipeIngredient;
    @Autowired
    RecipeCategoryRepository testRecipeCategory;

    Ingredient createdIngredient1;
    Ingredient createdIngredient2;
    Ingredient createdIngredient3;
    Ingredient createdIngredient4;
    Ingredient createdIngredient5;
    Recipe createdRecipe1;
    Recipe createdRecipe2;
    RecipeInstruction createdRecipeInstruction1;
    RecipeInstruction createdRecipeInstruction2;
    RecipeIngredient createdRecipeIngredient1;
    RecipeIngredient createdRecipeIngredient2;
    RecipeIngredient createdRecipeIngredient3;
    RecipeIngredient createdRecipeIngredient4;
    RecipeIngredient createdRecipeIngredient5;
    RecipeCategory createdCategory1;
    RecipeCategory createdCategory2;
    RecipeCategory createdCategory3;
    @BeforeEach
    public void setup() {

        //create db for ingredient
        Ingredient ingredientData1 = new Ingredient("sugar");
        Ingredient ingredientData2 = new Ingredient("milk");
        Ingredient ingredientData3 = new Ingredient("flour");
        Ingredient ingredientData4 = new Ingredient("butter");
        Ingredient ingredientData5 = new Ingredient("apple");
        createdIngredient1 = testIngredient.save(ingredientData1);
        createdIngredient2 = testIngredient.save(ingredientData2);
        createdIngredient3 = testIngredient.save(ingredientData3);
        createdIngredient4 = testIngredient.save(ingredientData4);
        createdIngredient5 = testIngredient.save(ingredientData5);

        //create db for recipeInstruction
        RecipeInstruction recipeInstructionData1 = new RecipeInstruction("mix apple, sugar, flour and butter then baking");
        RecipeInstruction recipeInstructionData2 = new RecipeInstruction("mix sugar, flour, milk then cook it on the pan");
        createdRecipeInstruction1 = testRecipeInstruction.save(recipeInstructionData1);
        createdRecipeInstruction2 = testRecipeInstruction.save(recipeInstructionData2);

        //create db for recipe
        Recipe recipeData1 = new Recipe("apple cake", createdRecipeInstruction1);
        Recipe recipeData2 = new Recipe("pan cake", createdRecipeInstruction2);
        createdRecipe1 = testRecipe.save(recipeData1);
        createdRecipe2 = testRecipe.save(recipeData2);

        //create db for recipeIngredient
        RecipeIngredient recipeIngredientData1 = new RecipeIngredient(createdIngredient1, 2, Measurement.KG);
        RecipeIngredient recipeIngredientData2 = new RecipeIngredient(createdIngredient2, 10, Measurement.DL);
        RecipeIngredient recipeIngredientData3 = new RecipeIngredient(createdIngredient3, 100, Measurement.DL);
        RecipeIngredient recipeIngredientData4 = new RecipeIngredient(createdIngredient4, 500, Measurement.G);
        RecipeIngredient recipeIngredientData5 = new RecipeIngredient(createdIngredient5,1 , Measurement.KG);
        createdRecipeIngredient1 = testRecipeIngredient.save(recipeIngredientData1);
        createdRecipeIngredient2 = testRecipeIngredient.save(recipeIngredientData2);
        createdRecipeIngredient3 = testRecipeIngredient.save(recipeIngredientData3);
        createdRecipeIngredient4 = testRecipeIngredient.save(recipeIngredientData4);
        createdRecipeIngredient5 = testRecipeIngredient.save(recipeIngredientData5);

        //create db for recipeCategory
        RecipeCategory recipeCategoryData1 = new RecipeCategory("Dessert");
        RecipeCategory recipeCategoryData2 = new RecipeCategory("A-la-cart");
        RecipeCategory recipeCategoryData3 = new RecipeCategory("Weekend");
        createdCategory1 = testRecipeCategory.save(recipeCategoryData1);
        createdCategory2 = testRecipeCategory.save(recipeCategoryData2);
        createdCategory3 = testRecipeCategory.save(recipeCategoryData3);
    }


    //todo: test in Ingredient Repository as following:
        //todo: Search for one ingredient object that matches exactly with sent in ingredient name => Done
        //todo: Search for one ingredient object that matches exactly with sent in ingredient name => Done

    //todo: test in Recipe Repository as following:
        //todo: Search for one ingredient object that matches exactly with sent in ingredient name
        //todo: Search for all recipes that contains a specified ingredient name e.g. potato, tomato, salt, etc.
        //todo: Search for all recipes that belong to a specific recipe category e.g. Chicken, Vegan, Celebration, Weekend etc.
        //todo: Search for all recipes that match one or more categories e.g. {”Spicy”,”Mexican”,”Weekend”}

    //todo: test add/remove RecipeIngredient in Ingredient class

    //todo: test add/remove RecipeIngredient in Recipe class

    //todo: test add/remove RecipeCategory in Recipe class

    //todo: test add/remove Recipe in RecipeCategory class

/*    @Test // findById is basic CRUD method from Spring, so we do not really neccessary to test it
    public void test_findById(){
        Optional<Recipe> recipeOptional = testRecipe.findById(createdRecipe1.getId());
        assertTrue(recipeOptional.isPresent());
        Recipe actualData = recipeOptional.get();
        Recipe expectedData = createdRecipe1;
        assertEquals(expectedData, actualData);
    }
    
    @Test // delete is basic CRUD method from Spring, so we do not really neccessary to test it
    public void test_remove_recipe(){
        Optional<Recipe> recipeOptional = testRecipe.findById(createdRecipe1.getId());
        assertTrue(recipeOptional.isPresent());
        testRecipe.delete(recipeOptional.get());
    }
    
    @Test
    public void test_add_RecipeIngredient(){
        createdRecipe.addRecipeIngredient(createdRecipeIngredient);
    }

    @Test
    public void test_addCategory(){
        createdRecipe.addRecipeCategory(createdCategory);
    }

    @Test
    public void TestFindAllByRecipeName() {

        List<Recipe> actualData = testRecipe.findAllByRecipeNameContainsIgnoreCase("Cake");

        List<Recipe> expectedData = new ArrayList<>();
        expectedData.add(createdRecipe);

        assertEquals(expectedData, actualData);
    }


    @Test
    public void TestFindByCategory() {
        Set<Recipe> actualData = testRecipe.findAllByCategoriesIsContaining(createdCategory);
        Set<Recipe> expectedData = new HashSet<>();
        expectedData.add(createdRecipe);
        assertEquals(expectedData, actualData);
    }
    @Test
    public void TestFindByCategories() {

        // todo fix this ... Search for all recipes that match one or more categories.

        System.out.println("#########################");

        Collection<String> categories = new ArrayList<>();
        categories.add("categoryTest");


        Set<Recipe> actualData = testRecipe.findAllByCategoriesIgnoreCase(categories); // string is null
        System.out.println("#########################");

        Recipe expectedData = createdRecipe;

        assertEquals(expectedData, actualData);

}*/



}
