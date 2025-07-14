package com.deliverysl.luxurydelivery.restaurant.model;

import com.deliverysl.luxurydelivery.category.model.Category;
import com.deliverysl.luxurydelivery.model.Employee;
import com.deliverysl.luxurydelivery.type.model.Type;
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
public class Restaurant {

    @Id @GeneratedValue
    private Long id;

    private String name;
    private String avatar;
    private String location;
    private double rating;
    private String description;
    private String phone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id")
    private Type type;

    @OneToMany(
            mappedBy = "restaurant",
            cascade = CascadeType.ALL,//Elimina todos los empleados que pertenezcan al restaurante si este se borra
            orphanRemoval = true
    )
    private List<Employee> employeeList;

    @OneToMany(
            mappedBy = "restaurant",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Category> categoryList;


}
