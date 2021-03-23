package com.example.complaint.controller;

// coming from master
import com.example.complaint.dto.EmailDto;
import com.example.complaint.entity.User;
import com.example.complaint.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;

    }

    @GetMapping("/users")
    public List<User> getUsers(){
        return userService.findAll();
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable int id){
       return userService.getUser(id);
    }

   // @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/users")
    public void addUser(@RequestBody User user){
        userService.saveUser(user);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
        userService.deleteUser(id);
    }

    @DeleteMapping("/users")
    public void deleteAllUsers(){
        userService.deleteAll();
    }

    @PutMapping("users/{id}")
    public void updateUser(@PathVariable int id, @RequestBody User user){
        userService.updateUser(id, user);
    }

    @PatchMapping("/users/{id}")
    public void updateUserEmail(@PathVariable int id, @RequestBody EmailDto email){
        userService.updateUserEmail(id, email.getEmail());

    }
}
