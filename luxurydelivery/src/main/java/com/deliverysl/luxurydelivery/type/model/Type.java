package com.deliverysl.luxurydelivery.type.model;

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
public class Type {

    @Id @GeneratedValue
    private Long id;

    private String name;
    private String description;

    @OneToMany(
            mappedBy = "type",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Restaurant> restaurantList;
}
