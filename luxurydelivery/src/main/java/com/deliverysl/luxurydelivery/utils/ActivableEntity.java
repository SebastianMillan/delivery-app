package com.deliverysl.luxurydelivery.utils;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@MappedSuperclass// No persiste como entidad en la BD
@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class ActivableEntity {

    @Builder.Default //Siempre al construirse una entidad tendrá como valor active = true
    @Column(nullable = false)
    protected boolean active = true;
}
