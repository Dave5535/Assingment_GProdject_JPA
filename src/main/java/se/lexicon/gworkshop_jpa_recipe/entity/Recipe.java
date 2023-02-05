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

@Data // contains @Setter, @Getter, @EqualsAndHashCode, @ToString + RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String recipeName;


    @OneToMany(mappedBy = "id", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private List<RecipeIngredient> recipeIngredients = new ArrayList<>();


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private RecipeInstruction instruction;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "recipes_categories",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<RecipeCategory> categories = new HashSet<>();

    public Recipe(String recipeName, RecipeInstruction instruction) {
        this.recipeName = recipeName;
        this.instruction = instruction;
    }

    public void addRecipeIngredient(RecipeIngredient recipeIngredient){
        if (recipeIngredients.contains(recipeIngredient)) throw new DataDuplicateException("Data Duplicate Exception");
        recipeIngredients.add(recipeIngredient);
       recipeIngredient.setRecipe(this);// tell to the other side to update info / set id to recipeIngredient and to Ingredient you added

    }

    public void removeRecipeIngredient(RecipeIngredient recipeIngredient){
        if (!recipeIngredients.contains(recipeIngredient)) throw new DataNotFoundException("Data Not Found Exception");
        recipeIngredients.remove(recipeIngredient);
        recipeIngredient.setRecipe(null);// tell to the other side to update info

    }

    public void addRecipeCategory(RecipeCategory recipeCategory){
        if (categories.contains(recipeCategory)) throw new DataDuplicateException("Data Duplicate Exception");
        categories.add(recipeCategory);

    }

    public void removeRecipeCategory(RecipeCategory recipeCategory){
        if (!categories.contains(recipeCategory)) throw new DataNotFoundException("Data Not Found Exception");
        categories.remove(recipeCategory);
    }

}
