package com.group.libraryapp.service.user;

import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepstrory;
import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.repstory.user.userloan.UserLoanHistoryRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServicev2 {
    @Autowired
    private final UserRepstrory userRepsotory;
    @Autowired


    public UserServicev2(UserRepstrory userRepsotory) {
        this.userRepsotory = userRepsotory;

    }

    @Transactional
    public void SaveUser(UserCreateRequest request) {
        userRepsotory.save(new User(request.getName(), request.getAge()));
    }
    @Transactional
    public List<UserResponse> getUser() {
        return userRepsotory.findAll().stream().map(user -> new UserResponse(user.getId(), user.getName(), user.getAge()))
                .collect(Collectors.toList());
    }
    @Transactional
    public void UpdateUser(UserUpdateRequest userUpdateRequest) {
        User user = userRepsotory.findById(userUpdateRequest.getId()).orElseThrow(IllegalArgumentException::new);
        user.update(userUpdateRequest.getName());
        userRepsotory.save(user);
    }
    @Transactional
    public void DeleteUser(String name) {
        User user = userRepsotory.findByName(name).orElseThrow(IllegalArgumentException::new);
        if (user == null) {
            throw new IllegalArgumentException(String.format("아이디없음"));
        }
        userRepsotory.delete(user);
    }
}