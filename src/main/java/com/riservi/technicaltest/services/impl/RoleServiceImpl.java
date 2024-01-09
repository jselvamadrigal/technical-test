package com.riservi.technicaltest.services.impl;

import com.riservi.technicaltest.domain.entities.Role;
import com.riservi.technicaltest.domain.models.Response;
import com.riservi.technicaltest.domain.repositories.RoleRepository;
import com.riservi.technicaltest.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(@Autowired RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Response<List<Role>> getAll() {
        var response = new Response<List<Role>>();

        response.setSuccess(true);
        response.setData(this.roleRepository.findAll());

        return response;
    }

    @Override
    public Response<Role> getRoleById(Long id) {
        var roleOptional = this.roleRepository.findById(id);
        var response = new Response<Role>();

        if (roleOptional.isPresent()) {
            response.setData(roleOptional.get());
            response.setSuccess(true);
        } else {
            response.setSuccess(false);
            response.setMessage("El rol con id (%s) no existe".formatted(id));
        }

        return response;
    }
}
