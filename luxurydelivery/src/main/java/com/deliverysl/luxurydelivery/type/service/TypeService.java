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

import java.util.List;

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
        type.setActivate(true);
        return save(type);
    }

   @Transactional
   public Type edit(TypeCreateDTO typeCreateDTO, Long id){
       Type type = findByIdOrThrow(id);
       type.setName(typeCreateDTO.name());
       type.setDescription(typeCreateDTO.description());
       return save(type);

   }

   @Transactional
   public void deleteTypeById(Long id){

        Type type = findByIdOrThrow(id);
        if (type.getName().trim().equalsIgnoreCase("Sin tipo")){
            //Se deberia crear otra excepción especifica para que
            //se lance cuando se intente borrar el tipo 'Sin tipo'
            throw new TypeNotFoundException(id);
        }
        else{
           Type noType = findByName("Sin tipo");
           noType.getRestaurantList().addAll(type.getRestaurantList());
           //Se asigna el 'Sin tipo' a todos los restaurantes de la lista
           for (Restaurant restaurant: type.getRestaurantList()){
               restaurant.setType(noType);
           }
           //No borramos el tipo,lo desactivamos
           type.setActivate(false);
           save(noType);
           save(type);
        }
   }

   @Transactional
   public Type activateType(Long id){
        Type type = findByIdOrThrow(id);
        if (!type.isActivate()){
            type.setActivate(true);
            return save(type);
        }
        return type;
   }

    public Type findByName(String name){
        return typeRepository.findByName(name).orElseThrow(TypeNotFoundException::new);
    }

    public List<Type> findByActivateTrue(){
        return typeRepository.findByActivateTrue();
    }


    public List<Type> findByActivateFalse(){
        return typeRepository.findByActivateFalse();
    }

}
