package com.deliverysl.luxurydelivery.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor //Constructor vacío
@AllArgsConstructor //Constructor completo
@SuperBuilder //Instanciar clases heredadas de esta
@Inheritance( // Crea una tabla por cada subclase
        strategy = InheritanceType.JOINED
)
@Entity
@Table(
        name = "usuario"
)
public abstract class User {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    private String name;
    private String surnames;
    private String phone;
    private String email;
    private String password;
    private String avatar;

}
