package com.example.dms.infrastructure.security.controller;

import com.example.dms.data.GeneralResponse;
import com.example.dms.infrastructure.security.data.DTO.UserDTO;
import com.example.dms.infrastructure.security.data.request.LoginRequest;
import com.example.dms.infrastructure.security.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authenticate/v1/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/all")
    public GeneralResponse allUsers(){
        return userService.allUsers();
    }

    @PostMapping("/login")
    public GeneralResponse login(@RequestBody LoginRequest loginRequest){
        return userService.login(loginRequest);
    }

    @GetMapping("/get/{id}")
    public GeneralResponse getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @PostMapping("/create")
    public GeneralResponse createUSer(@RequestBody UserDTO userDTO){
        return userService.createUser(userDTO);
    }

    @PutMapping("/update/{id}")
    public GeneralResponse updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO){
        return userService.updateUser(id, userDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
         userService.deleteUser(id);
    }

}
