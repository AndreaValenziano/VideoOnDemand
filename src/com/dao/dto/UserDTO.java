package com.dao.dto;

import com.videoondemand.model.User;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by AndreaValenziano on 18/12/17.
 */
public class UserDTO {


    public User user;
    public String username, password;
    public int role;
    public Map<Integer,String> roles = new HashMap<>();
    public int id;

    public UserDTO(User user) {
        this.user = user;
        this.username = user.getUsername();
        this.password = user.getPassword();
        roles.put(1,"Admin");
        roles.put(2,"Guest");
    }

    public UserDTO(String username, String password, int role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
