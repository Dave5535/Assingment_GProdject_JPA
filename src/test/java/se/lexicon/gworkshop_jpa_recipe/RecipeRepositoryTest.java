package se.lexicon.gworkshop_jpa_recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.lexicon.gworkshop_jpa_recipe.entity.*;
import se.lexicon.gworkshop_jpa_recipe.repository.*;
import java.util.*;import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
public class RecipeRepositoryTest {
    @Autowired RecipeRepository testRecipe;
    @Autowired    IngredientRepository testIngredient;
    @Autowired    RecipeInstructionRepository testRecipeInstruction;
    @Autowired    RecipeIngredientsRepository testRecipeIngredient;
    @Autowired    RecipeCategoryRepository testRecipeCategory;

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
    List<Recipe> recipeList = new ArrayList<>();
    Set<RecipeCategory> categories = new HashSet<>();
    @BeforeEach    public void setup() {
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
        // create db for recipeInstruction
        RecipeInstruction recipeInstructionData1 = new RecipeInstruction("mix apple, sugar, flour and butter then baking");
         RecipeInstruction recipeInstructionData2 = new RecipeInstruction("mix sugar, flour, milk then cook it on the pan");
         createdRecipeInstruction1 = testRecipeInstruction.save(recipeInstructionData1);
         createdRecipeInstruction2 = testRecipeInstruction.save(recipeInstructionData2);
        // create db for recipe
         Recipe recipeData1 = new Recipe("apple cake", createdRecipeInstruction1);
         Recipe recipeData2 = new Recipe("pan cake", createdRecipeInstruction2);
         createdRecipe1 = testRecipe.save(recipeData1);
         createdRecipe2 = testRecipe.save(recipeData2);
        // create db for recipeIngredient
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
        // create db for recipeCategory
        RecipeCategory recipeCategoryData1 = new RecipeCategory("Dessert");
        RecipeCategory recipeCategoryData2 = new RecipeCategory("A-la-cart");
        RecipeCategory recipeCategoryData3 = new RecipeCategory("Weekend");
         createdCategory1 = testRecipeCategory.save(recipeCategoryData1);
        createdCategory2 = testRecipeCategory.save(recipeCategoryData2);
         createdCategory3 = testRecipeCategory.save(recipeCategoryData3);
        // add recipe lists to category 1 and 2

        recipeList.add(createdRecipe1);
         createdCategory1.setRecipes(recipeList);
         createdCategory2.setRecipes(recipeList);
        // create category list
        categories.add(createdCategory1);
         categories.add(createdCategory2);
         categories.add(createdCategory3);    }

        // todo: test in Ingredient Repository as following:
// todo: Search for one ingredient object that matches exactly with sent in ingredient name => Done
// todo: Search for one ingredient object that matches exactly with sent in ingredient name => Done
// todo: test in Recipe Repository as following:
// todo: Search for one ingredient object that matches exactly with sent in ingredient name
// todo: Search for all recipes that contains a specified ingredient name e.g. potato, tomato, salt, etc.
// todo: Search for all recipes that belong to a specific recipe category e.g. Chicken, Vegan, Celebration, Weekend etc.
// todo: Search for all recipes that match one or more categories e.g. {”Spicy”,”Mexican”,”Weekend”}
        @Test
    public void test_findRecipesPresentInCategoriesIgnoreCaseWhitText() {
        Set<Recipe> expectedFoundRecipes = new HashSet<>();
        expectedFoundRecipes.add(createdRecipe1);
        Set<Recipe> actualFoundRecipes = testRecipe.findRecipesPresentInCategoriesIgnoreCase("apple cake");
        assertEquals(expectedFoundRecipes,actualFoundRecipes);
        System.out.println(actualFoundRecipes);}
         @Test
         public void test_findAllRecipesPresentInCategoriesIgnoreCase() {
        Set<Recipe> expectedFoundRecipes = new HashSet<>();
        expectedFoundRecipes.add(createdRecipe1);

        Set<Recipe> actualFoundRecipes = testRecipe.findAllRecipesPresentInCategoriesIgnoreCase(createdRecipe1);
        assertEquals(expectedFoundRecipes,actualFoundRecipes);
        System.out.println(actualFoundRecipes);
    }


//todo: test add/remove RecipeIngredient in Ingredient class
@Test
public void test_addCategory(){
    createdRecipe1.addRecipeCategory(new RecipeCategory("Testing"));

    System.out.println(createdRecipe1);
testRecipe.findRecipesPresentInCategoriesIgnoreCase("");

}
// todo: test add/remove RecipeIngredient in Recipe class
@Test
public void test_addRecipeIngredient(){
    Ingredient ingredient = new Ingredient("Salt");
    RecipeIngredient recipeIngredient = new RecipeIngredient(ingredient,3,Measurement.HG);
        createdRecipe1.addRecipeIngredient(recipeIngredient);  // give a StackOverflowError
    System.out.println(createdRecipe1);

}

// todo: test add/remove RecipeCategory in Recipe class
    @Test
public void test_removeCategory(){
        RecipeCategory recipeCategory = (new RecipeCategory("recipe"));
        createdRecipe1.addRecipeCategory(recipeCategory); // need to add a category because it is empty from the beginning
        createdRecipe1.removeRecipeCategory(recipeCategory); // should be empty after the removal

        assertEquals(new ArrayList<>(),createdRecipe1.getRecipeIngredients());

}
// todo: test add/remove Recipe in RecipeCategory class
    @Test
public void test_removeRecipeIngredient(){


}

}