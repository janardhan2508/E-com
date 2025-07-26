package com.app.ecom.controller;

import com.app.ecom.dto.UserRequest;
import com.app.ecom.dto.UserResponse;
import com.app.ecom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/api/users")
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        return new ResponseEntity<>(userService.fetchAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/api/users/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id){
       // User user =userService.fetchUsers(id);
       // if(user==null)
       //     return ResponseEntity.notFound().build();
       // return ResponseEntity.ok(user);

        return userService.fetchUsers(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build() );
    }

    @PostMapping("/api/users")
    public ResponseEntity<String> createUsers(@RequestBody UserRequest userRequest){
        userService.addUser(userRequest);
        return ResponseEntity.ok("User added successfully");
    }

    @PutMapping("/api/users/{id}")
    public ResponseEntity<String> updatedUser(@PathVariable Long id, @RequestBody UserRequest updateduserRequest){
        boolean updated = userService.updateUser(id,updateduserRequest);
        if(updated)
            return ResponseEntity.ok("user added successfully");
        return ResponseEntity.notFound().build();
    }
}
