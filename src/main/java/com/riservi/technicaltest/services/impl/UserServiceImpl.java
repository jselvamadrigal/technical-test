package com.riservi.technicaltest.services.impl;

import com.riservi.technicaltest.domain.entities.User;
import com.riservi.technicaltest.domain.models.Response;
import com.riservi.technicaltest.domain.models.UserRequest;
import com.riservi.technicaltest.domain.repositories.UserRepository;
import com.riservi.technicaltest.services.RoleService;
import com.riservi.technicaltest.services.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;

    public UserServiceImpl(@Autowired UserRepository userRepository, @Autowired RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    @Override
    public Response<User> getUserById(Long id) {
        var userOptional = this.userRepository.findById(id);
        var response = new Response<User>();

        if (userOptional.isPresent()) {
            response.setData(userOptional.get());
            response.setSuccess(true);
        } else {
            response.setSuccess(false);
            response.setMessage("El usuario con id (%s) no existe".formatted(id));
        }

        return response;
    }

    @Override
    public Response<Long> save(UserRequest userRequest) {
        var response = new Response<Long>();
        var validations = new ArrayList<String>();

        if (StringUtils.isBlank(userRequest.firstName())) {
            validations.add("firstName");
        }

        if (StringUtils.isBlank(userRequest.lastName())) {
            validations.add("lastName");
        }

        if (StringUtils.isBlank(userRequest.email())) {
            validations.add("email");
        }

        if (StringUtils.isBlank(userRequest.phone())) {
            validations.add("phone");
        }

        if (!validations.isEmpty()) {
            var message = "Los campos %s son requeridos".formatted(String.valueOf(validations));

            response.setSuccess(false);
            response.setMessage(message);
            return response;
        }

        var user = new User();
        user.setFirstName(userRequest.firstName());
        user.setLastName(userRequest.lastName());
        user.setEmail(userRequest.email());
        user.setPhone(userRequest.phone());

        user = this.userRepository.save(user);
        response.setSuccess(true);
        response.setData(user.getId());

        return response;
    }

    @Override
    public Response<List<User>> getAll() {
        var response = new Response<List<User>>();

        response.setSuccess(true);
        response.setData(this.userRepository.findAll());

        return response;
    }

    @Override
    public Response<Boolean> delete(Long id) {
        var userSearch = this.getUserById(id);
        var response = new Response<Boolean>();

        if (userSearch.isSuccess()) {
            var user = userSearch.getData();
            this.userRepository.delete(user);

            response.setData(true);
            response.setSuccess(true);
            response.setMessage("El usuario con id (%s) ha sido eliminado".formatted(id));
        } else {
            response.setData(false);
            response.setSuccess(false);
            response.setMessage(userSearch.getMessage());
        }

        return response;
    }

    @Override
    public Response<String> assignRole(Long userId, Long roleId) {
        var userSearch = this.getUserById(userId);
        var response = new Response<String>();

        if (userSearch.isSuccess()) {
            var roleSearch = this.roleService.getRoleById(roleId);

            if (roleSearch.isSuccess()) {

                var user = userSearch.getData();
                var role = roleSearch.getData();

                var roles = user.getRoles();
                var roleExists = roles.stream().filter(role1 -> role1.getId().equals(role.getId())).count();

                if (roleExists == 0) {
                    roles.add(role);
                    user.setRoles(roles);

                    this.userRepository.save(user);

                    response.setData("Rol asignado");
                    response.setSuccess(true);
                } else {
                    var message = "El usuario ya posee el rol que intenta asignar";
                    response.setData(message);
                    response.setSuccess(false);
                    response.setMessage(message);
                }
            } else {
                response.setData(roleSearch.getMessage());
                response.setSuccess(false);
                response.setMessage(roleSearch.getMessage());
            }
        } else {
            response.setData(userSearch.getMessage());
            response.setSuccess(false);
            response.setMessage(userSearch.getMessage());
        }

        return response;
    }

    @Override
    public Response<String> removeRole(Long userId, Long roleId) {
        var userSearch = this.getUserById(userId);
        var response = new Response<String>();

        if (userSearch.isSuccess()) {
            var roleSearch = this.roleService.getRoleById(roleId);

            if (roleSearch.isSuccess()) {

                var user = userSearch.getData();
                var role = roleSearch.getData();

                var roles = user.getRoles();
                var roleExists = roles.stream().filter(role1 -> role1.getId().equals(role.getId())).count();

                if (roleExists > 0) {
                    roles.remove(role);
                    user.setRoles(roles);

                    this.userRepository.save(user);

                    response.setData("Rol removido");
                    response.setSuccess(true);
                } else {
                    var message = "El usuario no posee el rol que intenta remover";
                    response.setData(message);
                    response.setSuccess(false);
                    response.setMessage(message);
                }
            } else {
                response.setData(roleSearch.getMessage());
                response.setSuccess(false);
                response.setMessage(roleSearch.getMessage());
            }
        } else {
            response.setData(userSearch.getMessage());
            response.setSuccess(false);
            response.setMessage(userSearch.getMessage());
        }

        return response;
    }
}
