package com.deliverysl.luxurydelivery.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
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
public class Rider extends User{

    private String dni;
    private String location;
    private boolean active;

    @OneToMany(
            mappedBy = "rider",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Order> orderList;

}
