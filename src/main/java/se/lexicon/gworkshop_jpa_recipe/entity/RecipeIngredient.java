package se.lexicon.gworkshop_jpa_recipe.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
public class RecipeIngredient {
    
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    @ManyToOne(cascade = {CascadeType.ALL})
    private Ingredient ingredient;
    @Column
    private double amount;
    private Measurement measurement;
    
    public RecipeIngredient( Ingredient ingredient, double amount, Measurement measurement ) {
        this.amount = amount;
        this.ingredient = ingredient;
        this.measurement = measurement;
    }
}
