package se.lexicon.gworkshop_jpa_recipe.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
