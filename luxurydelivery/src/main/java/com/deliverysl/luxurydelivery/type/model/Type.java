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
public class Type  {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(unique = true)
    private String name;

    private String description;

    @OneToMany(
            mappedBy = "type",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Restaurant> restaurantList;

    @Column(nullable = false)
    private boolean activate;

    //Metodos helper

    public void addRestaurant(Restaurant restaurant){
        this.restaurantList.add(restaurant);
        restaurant.setType(this);
    }

    /*public void deleteRestaurant(Restaurant restaurant){
        this.restaurantList.remove(restaurant);
        restaurant.setType(null);
    }*/

}
