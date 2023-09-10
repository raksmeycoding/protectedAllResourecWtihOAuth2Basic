package com.g3.springandangular_group3_ams.service.serviceimp;

import com.g3.springandangular_group3_ams.exception.BadRequestException;
import com.g3.springandangular_group3_ams.exception.NotFoundException;
import com.g3.springandangular_group3_ams.model.dto.UserDto;
import com.g3.springandangular_group3_ams.model.entity.UserApp;
import com.g3.springandangular_group3_ams.model.enums.Role;
import com.g3.springandangular_group3_ams.model.request.UserRequest;
import com.g3.springandangular_group3_ams.repository.UserRepository;
import com.g3.springandangular_group3_ams.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;

    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto create(UserRequest userRequest) {
        if (!userRequest.getName().isEmpty() && userRequest.getName().isBlank()) {
            throw new BadRequestException(
                    "Name invalid",
                    "name can't null or empty"
            );
        }

        if (userRequest.getRole() != Role.TEACHER && userRequest.getRole() != Role.READER) {
            throw new BadRequestException(
                    "Role invalid",
                    "Role must be one of [ TEACHER ] or [ READER ]"
            );
        }

        UserApp userApp = userRequest.toEntity();
        return userRepository.save(userApp).toDto();
    }

    @Override
    public List<UserDto> getAllUser(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo-1, size);
        Page<UserDto> pageResult = userRepository.findAll(pageable).map(UserApp::toDto);
        return pageResult.getContent();
    }

    @Override
    public Long getUserCount() {
        return userRepository.count();
    }

    @Override
    public UserDto getUserById(UUID Id) {
        UserApp user = userRepository.findById(Id).orElseThrow(
                () -> new NotFoundException("Not Found","User Id Not Found"));

        if(user != null){
            return userRepository.findById(Id).get().toDto();
        }
        return null;
    }

    @Override
    public UserDto updateUser(UUID Id, UserRequest userRequest) {
        UserApp userId = userRepository.findById(Id).orElseThrow(
                () -> new NotFoundException("Not Found","User Id Not Found")
        );
        if(userId != null){
            var userEntity = userRequest.toEntityById(userId.getId());
            return userRepository.save(userEntity).toDto();
        }
        return null;
    }

    @Override
    public UserDto deleteUser(UUID Id) {
        UserApp userId = userRepository.findById(Id).orElseThrow(
                () -> new NotFoundException("Not Found","User Id Not Found")
        );
        if(userId != null){
            userRepository.deleteById(userId.getId());
        }
        return null;
    }


}
