package com.deliverysl.luxurydelivery.allergen.model;

import com.deliverysl.luxurydelivery.product.model.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Allergen {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    private String name;
    private String description;
    private String image;

    @ManyToMany(
            mappedBy = "allergensList"
    )
    private List<Product>productList;



}
