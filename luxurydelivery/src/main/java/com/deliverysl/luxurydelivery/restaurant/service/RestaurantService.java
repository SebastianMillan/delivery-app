package com.deliverysl.luxurydelivery.restaurant.service;

import com.deliverysl.luxurydelivery.restaurant.dto.RestaurantDTO;
import com.deliverysl.luxurydelivery.restaurant.exception.RestaurantNotFoundException;
import com.deliverysl.luxurydelivery.restaurant.mapper.RestaurantMapper;
import com.deliverysl.luxurydelivery.restaurant.model.Restaurant;
import com.deliverysl.luxurydelivery.restaurant.repository.RestaurantRepository;
import com.deliverysl.luxurydelivery.type.model.Type;
import com.deliverysl.luxurydelivery.type.service.TypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final TypeService typeService;
    private final RestaurantMapper restaurantMapper;


    public List<Restaurant> findAll(){
       return restaurantRepository.findAll();
    }

    public Restaurant findById(Long id){
        return restaurantRepository.findById(id).orElseThrow(() -> new RestaurantNotFoundException(id));
    }

    public Restaurant create(RestaurantDTO restaurantDTO){

        Type type = typeService.findByName(restaurantDTO.type());

        return restaurantRepository.save(restaurantMapper.toEntity(restaurantDTO,type));

    }

    public Restaurant edit(RestaurantDTO restaurantDTO,Long id){

        Type type = typeService.findByName(restaurantDTO.type());

        return restaurantRepository.findById(id)
                .map(r ->{
                    r.setName(restaurantDTO.name());
                    r.setAvatar(restaurantDTO.avatar());
                    r.setRating(restaurantDTO.rating());
                    r.setType(type);
                    //r.setCategoryList();
                    return  restaurantRepository.save(r);
                })
                .orElseThrow(() -> new RestaurantNotFoundException(id));
    }

    public void delete (Long id){
        if (!restaurantRepository.existsById(id))
            throw new RestaurantNotFoundException(id);
        restaurantRepository.deleteById(id);
    }

}
