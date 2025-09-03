package com.deliverysl.luxurydelivery.user.model;

import com.deliverysl.luxurydelivery.order.model.Order;
import com.deliverysl.luxurydelivery.restaurant.model.Restaurant;
import com.fasterxml.jackson.annotation.JsonAlias;
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
@Entity
public class Employee extends User {

    private String dni;
    private boolean isBoss;

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
