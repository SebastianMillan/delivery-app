package com.deliverysl.luxurydelivery.product.model;

import com.deliverysl.luxurydelivery.category.model.Category;
import com.deliverysl.luxurydelivery.allergen.model.Allergen;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

// Le dice a Jackson que incluya en el JSON una propiedad "tipo" con el nombre del subtipo concreto (FOOD, DRINK, etc.)
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "tipo"
)
//Le dice a Jackson que cuando vea "tipo": "FOOD" en un JSON, debe deserializarlo como clase Food
@JsonSubTypes({
        @JsonSubTypes.Type(value = Food.class, name = "FOOD"),
        @JsonSubTypes.Type(value = Drink.class, name = "DRINK")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Inheritance(
        strategy = InheritanceType.JOINED
)
@Entity
public abstract class Product {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    protected Long id;

    protected String name;
    protected String description;
    protected String image;
    protected double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "category_id",
            nullable = false
    )
    protected Category category;

    @ManyToMany
    @JoinTable(
            name = "product_allergen",
            joinColumns = @JoinColumn(name="product_id"),
            inverseJoinColumns = @JoinColumn(name = "allergen_id")
    )
    protected List<Allergen> allergensList;

    // Los métodos helper ayudan a acomplar o desacomplar relaciones bidireccionales entre entidades
    public void addAllergen(Allergen allergen){
        this.allergensList.add(allergen);
        allergen.getProductList().add(this);
    }

    public void removeAllergen(Allergen allergen){
        this.allergensList.remove(allergen);
        allergen.getProductList().remove(this);
    }
}
