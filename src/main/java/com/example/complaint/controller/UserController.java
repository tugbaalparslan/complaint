package com.example.complaint.controller;

// coming from master
import com.example.complaint.dto.EmailDto;
import com.example.complaint.entity.User;
import com.example.complaint.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class UserController {
    /*
    private UserService userService;
    @Autowired
    public UserController(UserService userService){ // constructor dependency injection
        this.userService = userService;

    } */
    @Autowired
    private UserService userService; // field dependency injection

    @GetMapping("/users")
    public List<User> getUsers(){
        return userService.findAll();
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable int id){
       return userService.getUser(id);
    }

    @GetMapping("/users/search")
    public ResponseEntity<List<User>> searchUser(@RequestParam String phone){
        return userService.searchUserByPhone(phone);
    }

   // @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/users")
    public User addUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
        userService.deleteUser(id);
    }

    @DeleteMapping("/users")
    public void deleteAllUsers(){
        userService.deleteAll();
    }

    @PutMapping("/users/{id}")
    public void updateUser(@PathVariable int id, @RequestBody User user){
        userService.updateUser(id, user);
    }

    @PatchMapping("/users/{id}")
    public void updateUserEmail(@PathVariable int id, @RequestBody EmailDto email){
        userService.updateUserEmail(id, email.getEmail());
    }
}
