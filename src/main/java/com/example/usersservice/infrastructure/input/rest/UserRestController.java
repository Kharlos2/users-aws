package com.example.usersservice.infrastructure.input.rest;

import com.example.usersservice.application.dto.UserRequest;
import com.example.usersservice.application.dto.UserResponse;
import com.example.usersservice.application.dto.UserResponseIdRol;
import com.example.usersservice.application.handler.IUserHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/")
@RequiredArgsConstructor
public class UserRestController {
    private final IUserHandler userHandler;
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        return ResponseEntity.ok(userHandler.getAllUsers());
    }
    @GetMapping("{userId}")
    public ResponseEntity<UserResponseIdRol> getUserById(@PathVariable Long userId){
        return ResponseEntity.ok(userHandler.getUserById(userId));
    }
    @GetMapping("email/{email}")
    public ResponseEntity<UserResponse> getUserByEmail(@PathVariable String email){
        return ResponseEntity.ok(userHandler.getUserByEmail(email));
    }
    @PostMapping
    public ResponseEntity<Void> save(@RequestBody UserRequest request) {
        userHandler.saveUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @DeleteMapping
    public ResponseEntity<Void> delete(@PathVariable Long id){
        userHandler.deleteUser(id);
        return ResponseEntity.ok().build();
    }

}