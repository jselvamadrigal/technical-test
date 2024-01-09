package com.riservi.technicaltest.controllers;

import com.riservi.technicaltest.domain.entities.User;
import com.riservi.technicaltest.domain.models.Response;
import com.riservi.technicaltest.domain.models.UserRequest;
import com.riservi.technicaltest.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "user/")
public class UserController {

    private final UserService userService;

    public UserController(@Autowired UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<User>> getUserById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(this.userService.getUserById(id));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<Long>> save(@RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(this.userService.save(userRequest));
    }

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<Boolean>> delete(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(this.userService.delete(id));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<List<User>>> getAll() {
        return ResponseEntity.ok(this.userService.getAll());
    }

    @PutMapping(path = "/{userId}/role/{roleId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<String>> assignRole(@PathVariable(name = "userId") Long userId, @PathVariable(name = "roleId") Long roleId) {
        return ResponseEntity.ok(this.userService.assignRole(userId, roleId));
    }

    @DeleteMapping(path = "/{userId}/role/{roleId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<String>> removeRole(@PathVariable(name = "userId") Long userId, @PathVariable(name = "roleId") Long roleId) {
        return ResponseEntity.ok(this.userService.removeRole(userId, roleId));
    }

}
