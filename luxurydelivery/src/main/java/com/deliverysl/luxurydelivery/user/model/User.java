package com.deliverysl.luxurydelivery.user.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "tipo"
)

@JsonSubTypes({
        @JsonSubTypes.Type(value = Admin.class, name = "ADMIN"),
        @JsonSubTypes.Type(value = Employee.class, name = "EMPLOYEE"),
        @JsonSubTypes.Type(value = Client.class, name = "CLIENT"),
        @JsonSubTypes.Type(value = Rider.class, name = "RIDER")
})
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

    private boolean activate;

}
