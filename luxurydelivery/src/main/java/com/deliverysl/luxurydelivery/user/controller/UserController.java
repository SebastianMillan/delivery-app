package com.deliverysl.luxurydelivery.user.controller;

import com.deliverysl.luxurydelivery.user.dto.UserDTO;
import com.deliverysl.luxurydelivery.user.mapper.UserMapper;
import com.deliverysl.luxurydelivery.user.model.User;
import com.deliverysl.luxurydelivery.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usuario")
public class UserController
        implements UserControllerSwagger {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    @Override
    public ResponseEntity<List<UserDTO>> findAll(){
        List<User> userList = userService.findAll();
        return userList.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(userList.stream()
                        .map(userMapper::toDto)
                        .toList());
    }

    @GetMapping("/{id:[0-9]+}")
    @Override
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(userMapper.toDto(userService.findByIdOrThrow(id)));
    }

    @DeleteMapping("/{id:[0-9]+}")
    @Override
    public ResponseEntity<?> delete(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id:[0-9]+}/activate")
    @Override
    public ResponseEntity<UserDTO> activate(@PathVariable Long id) {
        return ResponseEntity.ok(userMapper.toDto(userService.activateUser(id)));
    }

    @GetMapping("/enable")
    @Override
    public ResponseEntity<List<UserDTO>> findByActivateTrue() {
        List<User> userList = userService.findByActivateTrue();
        return userList.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(userList.stream()
                        .map(userMapper::toDto)
                        .toList());
    }

    @GetMapping("/disable")
    @Override
    public ResponseEntity<List<UserDTO>> findByActivateFalse() {
        List<User> userList = userService.findByActivateFalse();
        return userList.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(userList.stream()
                        .map(userMapper::toDto)
                        .toList());
    }
}
