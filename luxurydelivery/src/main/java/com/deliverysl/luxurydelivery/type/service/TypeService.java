package com.deliverysl.luxurydelivery.type.service;

import com.deliverysl.luxurydelivery.type.exception.TypeNotFoundException;
import com.deliverysl.luxurydelivery.type.model.Type;
import com.deliverysl.luxurydelivery.type.repository.TypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TypeService {

    private final TypeRepository typeRepository;

    public List<Type> findAll(){
        return typeRepository.findAll();
    }

    public Type findById(Long id){
        return typeRepository.findById(id).orElseThrow(() -> new TypeNotFoundException(id));
    }

   public Type create (Type type){
       return typeRepository.save(type);
   }
   public Type edit(Type type, Long id){
        return typeRepository.findById(id)
                .map(t-> {
                    t.setName(type.getName());
                    t.setDescription(type.getDescription());
                    //t.setRestaurantList(type.getRestaurantList());
                    return typeRepository.save(t);
                })
                .orElseThrow(() -> new TypeNotFoundException(id));
   }

    public void delete (Long id){
        typeRepository.deleteById(id);
    }

    public Type findByName(String name){
        return typeRepository.findByName(name).orElseThrow(TypeNotFoundException::new);
    }

}
