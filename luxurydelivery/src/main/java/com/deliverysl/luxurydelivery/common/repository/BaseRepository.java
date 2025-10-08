package com.deliverysl.luxurydelivery.common.repository;

import com.deliverysl.luxurydelivery.common.model.ActivableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean //Al no estar ligado a ninguna entidad hacemos que no genere un Bean de algo abstracto Spring.
public interface BaseRepository<T extends ActivableEntity,ID>
        extends JpaRepository<T,ID>  {
        List<T> findAllByActiveTrue(); // Devuelve un listado con la entidad que tenga el campo active en true
}
