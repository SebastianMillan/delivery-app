package com.deliverysl.luxurydelivery.model;

import com.deliverysl.luxurydelivery.category.model.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Inheritance(
        strategy = InheritanceType.JOINED
)
@Entity
public abstract class Product {

    @Id @GeneratedValue
    private Long id;

    private String name;
    private String description;
    private String image; 
    private double price;

    @ManyToOne
    @JoinColumn(
            name = "category_id"
    )
    private Category category;

    @ManyToMany
    @JoinTable(
            name = "product_allergen",
            joinColumns = @JoinColumn(name="product_id"),
            inverseJoinColumns = @JoinColumn(name = "allergen_id")
    )
    private List<Allergen> allergensList;





}
