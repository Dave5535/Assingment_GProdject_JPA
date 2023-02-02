package se.lexicon.gworkshop_jpa_recipe.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import se.lexicon.gworkshop_jpa_recipe.exception.DataDuplicateException;
import se.lexicon.gworkshop_jpa_recipe.exception.DataNotFoundException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data // contains @Setter, @Getter, @EqualsAndHashCode, @ToString + RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true, nullable = false)
    private String ingredientName;

    @OneToMany(mappedBy = "id", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private List<RecipeIngredient> recipeIngredients = new ArrayList<>();

    public Ingredient(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public void addRecipeIngredient(RecipeIngredient recipeIngredient){
        if (recipeIngredients.contains(recipeIngredient)) throw new DataDuplicateException("Data Duplicate Exception");
        recipeIngredients.add(recipeIngredient);
        recipeIngredient.setIngredient(this);// tell to the other side to update info

    }

    public void removeRecipeIngredient(RecipeIngredient recipeIngredient){
        if (!recipeIngredients.contains(recipeIngredient)) throw new DataNotFoundException("Data Not Found Exception");
        recipeIngredients.remove(recipeIngredient);
        recipeIngredient.setIngredient(null);// tell to the other side to update info

    }
}
