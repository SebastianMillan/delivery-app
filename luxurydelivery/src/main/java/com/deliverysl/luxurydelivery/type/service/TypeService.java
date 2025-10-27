package com.deliverysl.luxurydelivery.type.service;

import com.deliverysl.luxurydelivery.restaurant.model.Restaurant;
import com.deliverysl.luxurydelivery.restaurant.repository.RestaurantRepository;
import com.deliverysl.luxurydelivery.restaurant.service.RestaurantService;
import com.deliverysl.luxurydelivery.type.dto.TypeCreateDTO;
import com.deliverysl.luxurydelivery.type.exception.ProtectedTypeException;
import com.deliverysl.luxurydelivery.type.exception.TypeNotFoundException;
import com.deliverysl.luxurydelivery.type.mapper.TypeMapper;
import com.deliverysl.luxurydelivery.type.model.Type;
import com.deliverysl.luxurydelivery.type.repository.TypeRepository;
import com.deliverysl.luxurydelivery.utils.BaseServiceImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.deliverysl.luxurydelivery.utils.DefaultsIds.DEFAULT_TYPE_ID;

@Service
@RequiredArgsConstructor
public class TypeService extends BaseServiceImpl<Type,Long> {

    private final TypeMapper typeMapper;
    private final TypeRepository typeRepository;

    @Lazy
    @Autowired
    private RestaurantService restaurantService;

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

       if (id == DEFAULT_TYPE_ID) {throw new ProtectedTypeException(id);}

       Type type = findByIdOrThrow(id);
       type.setName(typeCreateDTO.name());
       type.setDescription(typeCreateDTO.description());
       return save(type);

   }

   @Transactional
   public void deactiveTypeById(Long id){

        if (id == DEFAULT_TYPE_ID){throw new ProtectedTypeException(id);}

        Type defaultType = findByIdOrThrow(DEFAULT_TYPE_ID);
        List<Restaurant> restaurantList = restaurantService.findAllByTypeId(id);
        restaurantList.forEach(res->{res.setDefaultType(defaultType);});
        deactivate(id);

   }

    public Type findByName(String name){
        return typeRepository.findByName(name).orElseThrow(TypeNotFoundException::new);
    }

}
