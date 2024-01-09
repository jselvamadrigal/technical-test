package com.riservi.technicaltest.controllers;

import com.riservi.technicaltest.domain.entities.Role;
import com.riservi.technicaltest.domain.models.Response;
import com.riservi.technicaltest.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "role/")
public class RoleController {

    private final RoleService roleService;

    public RoleController(@Autowired RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<List<Role>>> getAll() {
        return ResponseEntity.ok(this.roleService.getAll());
    }
}
