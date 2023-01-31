package se.lexicon.gworkshop_jpa_recipe.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import se.lexicon.gworkshop_jpa_recipe.exception.DataDuplicateException;
import se.lexicon.gworkshop_jpa_recipe.exception.DataNotFoundException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
public class Recipe {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String recipeName;
    @OneToMany(mappedBy = "id")
    private List<RecipeIngredient> recipeIngredients = new ArrayList<>();
    @OneToOne(cascade = CascadeType.ALL)
    private RecipeInstruction instruction;
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<RecipeCategory> categories = new HashSet<>();
    
    public Recipe( String recipeName, List<RecipeIngredient> recipeIngredients, RecipeInstruction instruction, Set<RecipeCategory> categories ) {
        this.recipeName = recipeName;
        this.recipeIngredients = recipeIngredients;
        this.instruction = instruction;
        this.categories = categories;
    }
    
    public void addRecipeIngredient(RecipeIngredient recipeIngredient){
        if(recipeIngredients.contains(recipeIngredient)){
            throw new DataDuplicateException("Data duplicate exception");
        }
        recipeIngredients.add(recipeIngredient);
    }
    
    public void removeRecipeIngredient(RecipeIngredient recipeIngredient){
        if(!recipeIngredients.contains(recipeIngredient)){
            throw new DataNotFoundException("Data not found");
        }
        recipeIngredients.remove(recipeIngredient);
    }
    
    public void addRecipeCategory(RecipeCategory recipeCategory){
        if(categories.contains(recipeCategory)){
            throw new DataDuplicateException("Data duplicate exception");
        }
        categories.add(recipeCategory);
    }
    
    public void removeRecipeCategory(RecipeCategory recipeCategory){
        if(!categories.contains(recipeCategory)){
            throw new DataNotFoundException("Data not found");
        }
        categories.remove(recipeCategory);
    }
}
