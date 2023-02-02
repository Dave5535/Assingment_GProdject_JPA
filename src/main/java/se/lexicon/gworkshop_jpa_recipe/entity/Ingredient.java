package se.lexicon.gworkshop_jpa_recipe.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data // contains @Setter, @Getter, @EqualsAndHashCode, @ToString + RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true, nullable = false)
    private String ingredientName;


    public Ingredient(String ingredientName) {
        this.ingredientName = ingredientName;
    }
}
