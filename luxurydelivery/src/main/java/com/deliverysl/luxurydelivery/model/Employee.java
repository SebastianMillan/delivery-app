package com.deliverysl.luxurydelivery.model;

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
public class Employee extends User {

    private String dni;
    private boolean isBoss;
    private boolean active;

    @ManyToOne(fetch = FetchType.LAZY) //No se trae los atributos de restaurante a no ser que posteriormente se realice una consulta.)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @OneToMany(
            mappedBy = "employee",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Order> orderList;

}
