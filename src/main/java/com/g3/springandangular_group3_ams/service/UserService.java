package com.g3.springandangular_group3_ams.service;

import com.g3.springandangular_group3_ams.model.dto.UserDto;
import com.g3.springandangular_group3_ams.model.entity.UserApp;
import com.g3.springandangular_group3_ams.model.request.UserRequest;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserDto create(UserRequest userRequest);
    List<UserDto> getAllUser(Integer pageNo, Integer size);
    Long getUserCount();
    UserDto getUserById(UUID Id);
    UserDto updateUser(UUID Id, UserRequest userRequest);
    UserDto deleteUser(UUID Id);

}
