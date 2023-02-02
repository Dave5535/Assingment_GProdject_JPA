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
public class RecipeCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String category;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "recipes_categories",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "recipe_id")
    )
    private List<Recipe> recipes = new ArrayList<>();



    public RecipeCategory(String category) {
        this.category = category;
    }

    public void addRecipe(Recipe recipe) {
        if (recipes.contains(recipe)) throw new DataDuplicateException("Data Duplicate Exception");
        recipes.add(recipe);

    }

    public void removeRecipe(Recipe recipe) {
        if (!recipes.contains(recipe)) throw new DataNotFoundException("Data Not Found Exception");
        recipes.remove(recipe);
    }
}
