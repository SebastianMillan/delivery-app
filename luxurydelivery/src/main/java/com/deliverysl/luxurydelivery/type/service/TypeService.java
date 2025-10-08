package com.deliverysl.luxurydelivery.type.service;

import com.deliverysl.luxurydelivery.restaurant.model.Restaurant;
import com.deliverysl.luxurydelivery.type.dto.TypeCreateDTO;
import com.deliverysl.luxurydelivery.type.exception.TypeNotFoundException;
import com.deliverysl.luxurydelivery.type.mapper.TypeMapper;
import com.deliverysl.luxurydelivery.type.model.Type;
import com.deliverysl.luxurydelivery.type.repository.TypeRepository;
import com.deliverysl.luxurydelivery.utils.BaseServiceImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.deliverysl.luxurydelivery.common.DefaultsIds.DEFAULT_TYPE_ID;

@Service
@RequiredArgsConstructor
public class TypeService extends BaseServiceImpl<Type,Long> {

    private final TypeMapper typeMapper;
    private final TypeRepository typeRepository;

    public Type findByIdOrThrow(Long id){
        return findOptionalById(id)
                .orElseThrow(() -> new TypeNotFoundException(id));
    }

   @Transactional
   public Type create (TypeCreateDTO typeCreateDTO){
        Type type = typeMapper.toEntity(typeCreateDTO);
        return save(type);
    }

   @Transactional
   public Type edit(TypeCreateDTO typeCreateDTO, Long id){

       if (id == DEFAULT_TYPE_ID) {throw new TypeNotFoundException(id);}

       Type type = findByIdOrThrow(id);
       type.setName(typeCreateDTO.name());
       type.setDescription(typeCreateDTO.description());
       return save(type);

   }

   @Transactional
   public void deactiveTypeById(Long id){

        if (id == DEFAULT_TYPE_ID){
            //Se deberia crear otra excepción especifica para que
            //se lance cuando se intente activar o desactivar el tipo 'Sin tipo'
            throw new TypeNotFoundException(id);
        }

        Type defaultType = findByIdOrThrow(DEFAULT_TYPE_ID);
        Type type = findByIdOrThrow(id);
        //Se añaden todos los restaurantes del tipo buscado al tipo por defecto
        defaultType.getRestaurantList().addAll(type.getRestaurantList());
        //Se asigna el 'Sin tipo' a todos los restaurantes de la lista
        for (Restaurant restaurant: type.getRestaurantList()){
            restaurant.setType(defaultType);
        }
        //No borramos el tipo,lo desactivamos
        save(defaultType);
        deactivate(id);

   }

    public Type findByName(String name){
        return typeRepository.findByName(name).orElseThrow(TypeNotFoundException::new);
    }

}
