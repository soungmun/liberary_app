package com.group.libraryapp.repstory.user;

import com.group.libraryapp.dto.user.response.UserResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserJdbcRepsotory {
    public boolean updateUser(JdbcTemplate jdbcTemplate, long id){
      String sqlup = "select *from user WHERE id= ?";
       return jdbcTemplate.query(sqlup, (rs, rowNum) -> 0, id).isEmpty();

    }

    public void updateUserName(JdbcTemplate jdbcTemplate, String name, long id ){
        String sql = "UPDATE user SET name = ? WHERE id= ?";
       jdbcTemplate.update(sql, name, id);
   }
    public boolean deleteUser(JdbcTemplate jdbcTemplate, String name){
        String sqlup = "select *from user WHERE name= ?";
        return jdbcTemplate.query(sqlup, (rs, rowNum) -> 0, name).isEmpty();
    }
    public List<UserResponse> getUser(JdbcTemplate jdbcTemplate) {
        String sql="select * from user";
        return jdbcTemplate.query(sql, new RowMapper<UserResponse>() {
            @Override
            public UserResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                return new UserResponse(id, name, age);
            }
        });
    }
    public void deleteUserName(JdbcTemplate jdbcTemplate, String name) {
        String sqlde = "DELETE FROM user WHERE name= ?";
        jdbcTemplate.update(sqlde, name);
    }

    public void SaveUser(JdbcTemplate jdbcTemplate, String name, int age){
        String sql = "INSERT INTO user (name, age) VALUES (?, ?)";
        jdbcTemplate.update(sql, name, age);
    }

}

