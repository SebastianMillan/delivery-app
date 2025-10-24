package com.deliverysl.luxurydelivery.restaurant.service;

import com.deliverysl.luxurydelivery.category.model.Category;
import com.deliverysl.luxurydelivery.user.service.EmployeeService;
import com.deliverysl.luxurydelivery.utils.DefaultsIds;
import com.deliverysl.luxurydelivery.restaurant.dto.CreateRestaurandDTO;
import com.deliverysl.luxurydelivery.restaurant.exception.RestaurantNotFoundException;
import com.deliverysl.luxurydelivery.restaurant.mapper.RestaurantMapper;
import com.deliverysl.luxurydelivery.restaurant.model.Restaurant;
import com.deliverysl.luxurydelivery.type.model.Type;
import com.deliverysl.luxurydelivery.type.service.TypeService;
import com.deliverysl.luxurydelivery.user.model.Employee;
import com.deliverysl.luxurydelivery.utils.BaseServiceImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService extends BaseServiceImpl<Restaurant,Long> {

    private final TypeService typeService;
    private final RestaurantMapper restaurantMapper;

    @Lazy
    @Autowired
    private EmployeeService employeeService;

    public Restaurant findByIdOrThrow(Long id){
        return findOptionalById(id)
                .orElseThrow(() -> new RestaurantNotFoundException(id));
    }

    @Transactional
    public Restaurant create(CreateRestaurandDTO createRestaurandDTO){

        Type type = typeService.findByName(createRestaurandDTO.type());
        Restaurant restaurant = restaurantMapper.toEntity(createRestaurandDTO,type);
        type.addRestaurant(restaurant);

        //el restaurante que se cree tendrá un categoría por defecto
        Category category = Category.builder()
                .name("Sin categoría")
                .description("Para productos sin categoría seleccionada")
                .restaurant(restaurant)
                .noCategory(true)
                .build();

        restaurant.addCategory(category);
        
        return save(restaurant);
    }

    @Transactional
    public Restaurant edit(CreateRestaurandDTO createRestaurandDTO,Long id){

        if (id == DefaultsIds.DEFAULT_RESTAURANT_ID){throw new RestaurantNotFoundException(id);}

        Type type = typeService.findByName(createRestaurandDTO.type());
        Restaurant restaurant = findByIdOrThrow(id);
        restaurant.setName(createRestaurandDTO.name());
        restaurant.setAvatar(createRestaurandDTO.avatar());
        restaurant.setRating(createRestaurandDTO.rating());
        restaurant.setType(type);

        return save(restaurant);

    }

    @Transactional
    public void deactiveRestaurantById(Long id){

        if (id == DefaultsIds.DEFAULT_RESTAURANT_ID){throw new RestaurantNotFoundException(id);}

        Restaurant defaultRestaurant = findByIdOrThrow(DefaultsIds.DEFAULT_RESTAURANT_ID);
        List<Employee> employees = employeeService.findAllByRestaurantId(id);

        //Nos ahorramos traer todo el restaurante ya que solo necesitamos sus empleados, además apoyandonos en el helper asignados el restaurante por defecto a esos empleados
        /*
        Restaurant restaurant = findByIdOrThrow(id);

        defaultRestaurant.getEmployeeList().addAll(restaurant.getEmployeeList());

        for(Employee employee:restaurant.getEmployeeList()){
            employee.setRestaurant(defaultRestaurant);
        }*/
        employees.forEach(emp -> emp.setDefaultRestaurant(defaultRestaurant));

        //Es posible quitar el save ya que la transacción detecta los cambios en entidades y los guarda en BBDD
        deactivate(id);
    }
}
