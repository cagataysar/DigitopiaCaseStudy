package com.digitopia.digitopiacasestudy.controller;

import com.digitopia.digitopiacasestudy.model.User;
import com.digitopia.digitopiacasestudy.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "user")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        this.userService.create(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PatchMapping
    public User updateUserByEmail(@PathVariable(name = "id") UUID id, String newEmail) {
        return userService.updateByUserID(id, newEmail);
    }

    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteByEmail(@RequestParam(name = "email") String email) {
        userService.deleteByEmail(email);
        return ResponseEntity.ok("Deleted Succesfully");
    }
}
