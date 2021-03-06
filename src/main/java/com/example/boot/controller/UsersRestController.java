package com.example.boot.controller;

import com.example.boot.model.User;
import com.example.boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class UsersRestController {

    private final UserService userService;

    @Autowired
    UsersRestController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/restUsers")
    public ResponseEntity<List<User>> restListUsers(){
        List<User> users = userService.listUser();
        return users != null && !users.isEmpty() ? new ResponseEntity<>(users, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/restUsers/{id}")
    public ResponseEntity<User> restOneUser(@PathVariable(name = "id")  long id){
        User user = userService.getUserById(id);
        return user != null ? new ResponseEntity<>(user,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/restUsers")
    public ResponseEntity<User> newUser(@RequestBody User user, @RequestParam String[] roleIds) {
        userService.addUser(user, roleIds);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/restUsers")
    public ResponseEntity<User> updateUser(@RequestBody User user, @RequestParam String[] roleIds) {
        userService.updateUser(user, roleIds);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/restUsers/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable(name = "id")  long id) {
        userService.removeUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
