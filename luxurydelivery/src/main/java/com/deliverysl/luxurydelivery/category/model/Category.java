package com.deliverysl.luxurydelivery.category.model;

import com.deliverysl.luxurydelivery.utils.ActivableEntity;
import com.deliverysl.luxurydelivery.product.model.Product;
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
public class Category extends ActivableEntity {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    private String name;
    private String description;

    //Nuevo atributo para obtener la categoria que se crea por defecto cuando se crea un restaurante
    //No busco por nombre en la BD ya que si se añaden traducciones en la aplicación
    //Petará 100X100
    @Builder.Default
    @Column(nullable = false)
    private boolean noCategory = false;

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

    //Metodos Helper
    public void addProduct(Product product){
        this.productList.add(product);
        product.setCategory(this);
    }

    public void deleteProduct(Product product){
        this.productList.remove(product);
        product.setCategory(null);
    }

}
