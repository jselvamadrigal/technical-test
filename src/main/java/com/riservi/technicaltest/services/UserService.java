package com.riservi.technicaltest.services;

import com.riservi.technicaltest.domain.entities.User;
import com.riservi.technicaltest.domain.models.Response;
import com.riservi.technicaltest.domain.models.UserRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    public Response<User> getUserById(Long id);

    public Response<Long> save(UserRequest userRequest);

    public Response<Boolean> delete(Long id);

    public Response<List<User>> getAll();

    public Response<String> assignRole(Long userId, Long roleId);

    public Response<String> removeRole(Long userId, Long roleId);
}
