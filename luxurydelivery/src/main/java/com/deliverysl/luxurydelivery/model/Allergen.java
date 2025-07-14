package com.deliverysl.luxurydelivery.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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

    @Id @GeneratedValue
    private Long id;

    private String name;
    private String description;
    private String image;

    @ManyToMany(
            mappedBy = "allergensList"
    )
    private List<Product>productList;



}
