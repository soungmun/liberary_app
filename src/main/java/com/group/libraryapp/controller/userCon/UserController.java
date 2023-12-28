package com.group.libraryapp.controller.userCon;


import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.service.user.UserServicev2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {



    private final UserServicev2 userServicev2;

    public UserController(UserServicev2 userServicev2) {
        this.userServicev2 = userServicev2;
    }

    @PostMapping("/user")
    public void save(@RequestBody UserCreateRequest request) {
        userServicev2.SaveUser(request);
    }

    @GetMapping("/user")
    public List<UserResponse> getUser() {
        return userServicev2.getUser();
    }
    @DeleteMapping("/user")
    public void delete(@RequestParam String name) {
        userServicev2.DeleteUser(name);
    }
    @PutMapping("/user")
    public void update(@RequestBody UserUpdateRequest request) {
        userServicev2.UpdateUser(request);
    }
}


