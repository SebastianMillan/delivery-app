package com.deliverysl.luxurydelivery.user.model;

import com.deliverysl.luxurydelivery.order.model.Order;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
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
public class Rider extends User{

    private String dni;
    private String location;

    @OneToMany(
            mappedBy = "rider",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Order> orderList;

}
