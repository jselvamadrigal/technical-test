package com.riservi.technicaltest.services;

import com.riservi.technicaltest.domain.entities.Role;
import com.riservi.technicaltest.domain.models.Response;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {
    Response<List<Role>> getAll();

    Response<Role> getRoleById(Long id);
}
