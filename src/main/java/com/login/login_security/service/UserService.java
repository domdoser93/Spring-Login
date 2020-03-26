package com.login.login_security.service;

import com.login.login_security.model.User;

public interface UserService {
    
    public Iterable getAllUsers();

    public User createUser(User user) throws Exception;
    
}
