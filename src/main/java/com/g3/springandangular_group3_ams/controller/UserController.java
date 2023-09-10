package com.g3.springandangular_group3_ams.controller;

import com.g3.springandangular_group3_ams.model.dto.UserDto;
import com.g3.springandangular_group3_ams.model.request.UserRequest;
import com.g3.springandangular_group3_ams.model.response.Response;
import com.g3.springandangular_group3_ams.model.response.ResponsePage;
import com.g3.springandangular_group3_ams.service.UserService;
import com.g3.springandangular_group3_ams.utils.Validation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("")
    public ResponseEntity<Response<?>> create(
            @RequestBody UserRequest userRequest
    ) {
        Response<?> response = Response.<UserDto>builder()
                .message("Insert user successfully")
                .status("201")
                .payload(userService.create(userRequest))
                .build();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("")
    public ResponseEntity<ResponsePage<?>> getAllUser(
            @RequestParam(defaultValue = "1") Integer pageNo,
            @RequestParam(defaultValue = "5") Integer size
    ){
        Validation.validatePagination(pageNo, size);
        var getUsers = userService.getAllUser(pageNo, size);
        int totalPages = (int) Math.ceil((double) userService.getUserCount() / size);
        ResponsePage<?> response = ResponsePage.<List<UserDto>>builder()
                .message("Get All User Successfully")
                .status("200")
                .payload(getUsers)
                .page(pageNo)
                .size(size)
                .totalElements(userService.getUserCount())
                .totalPage(totalPages)
                .build();
        return  ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<?>> getUserById(@PathVariable UUID id){
        Response<?> response = Response.<UserDto>builder()
                .message("Get User By Id Successfully")
                .status("200")
                .payload(userService.getUserById(id))
                .build();
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<?>> updateUser(@RequestParam UUID id, @RequestBody UserRequest userRequest){
        Response<?> response = Response.<UserDto>builder()
                .message("Update User Successfully")
                .status("200")
                .payload(userService.updateUser(id,userRequest))
                .build();
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<?>> deleteUser(@PathVariable UUID id){
        Response<?> response = Response.<UserDto>builder()
                .message("Delete User Successfully")
                .status("200")
                .payload(userService.deleteUser(id))
                .build();
        return ResponseEntity.ok().body(response);
    }
}
