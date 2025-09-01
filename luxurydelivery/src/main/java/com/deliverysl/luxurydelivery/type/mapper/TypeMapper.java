package com.deliverysl.luxurydelivery.type.mapper;

import com.deliverysl.luxurydelivery.type.dto.TypeCreateDTO;
import com.deliverysl.luxurydelivery.type.dto.TypeDTO;
import com.deliverysl.luxurydelivery.type.model.Type;
import org.springframework.stereotype.Component;

@Component
public class TypeMapper {

    public Type toEntity(TypeDTO typeDTO){
        return Type.builder()
                .id(typeDTO.id())
                .name(typeDTO.name())
                .description(typeDTO.description())
                .activate(typeDTO.activate())
                .build();
    }

    public TypeDTO toDto(Type type){
        return new TypeDTO(
                type.getId(),
                type.getName(),
                type.getDescription(),
                type.isActivate()
        );
    }

    public Type toEntity(TypeCreateDTO typeCreateDTO){
        return Type.builder()
                .name(typeCreateDTO.name())
                .description(typeCreateDTO.description())
                .build();
    }

    public TypeCreateDTO toCreateDto(Type type){
        return new TypeCreateDTO(
                type.getName(),
                type.getDescription()
        );
    }

}
