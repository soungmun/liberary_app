package com.group.libraryapp.service.user;

import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.repstory.user.UserJdbcRepsotory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServicev1 {


    private final UserJdbcRepsotory userRepsotory=new UserJdbcRepsotory();
   public void updateUser(JdbcTemplate jdbcTemplate, UserCreateRequest request){
        if(userRepsotory.updateUser(jdbcTemplate, request.getId())){
           throw new IllegalStateException(String.format("빈값입니다"));
        }
       userRepsotory .updateUserName(jdbcTemplate, request.getName(),request.getId());
     }
    public void deleteUser(JdbcTemplate jdbcTemplate, String name){
        if(userRepsotory.deleteUser(jdbcTemplate, name)){
            throw new IllegalStateException(String.format("빈값입니다"));
        }
        userRepsotory.deleteUserName(jdbcTemplate, name);
    }

    public void SaveUser(JdbcTemplate jdbcTemplate, UserCreateRequest request){
        userRepsotory.SaveUser(jdbcTemplate, request.getName(), request.getAge());
    }

    public List<UserResponse> getUser(JdbcTemplate jdbcTemplate) {
       return userRepsotory.getUser(jdbcTemplate);
    }
}

