package com.deliverysl.luxurydelivery.category.model;

import com.deliverysl.luxurydelivery.model.Product;
import com.deliverysl.luxurydelivery.restaurant.model.Restaurant;
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
public class Category {

    @Id @GeneratedValue
    private Long id;

    private String name;
    private String description;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "restaurant_id"
    )
    private Restaurant restaurant;

    @OneToMany(
            mappedBy = "category",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Product> productList;


}
