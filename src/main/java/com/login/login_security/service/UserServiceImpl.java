package com.login.login_security.service;

import com.login.login_security.model.User;
import com.login.login_security.repository.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author ipm-s
 */
@Service
public class UserServiceImpl implements UserService{
    
    @Autowired
    UserRepository userRepository;
    
    @Override
    public Iterable getAllUsers() {
        return userRepository.findAll();
    }
    
    private boolean checkUsernameExists(User user) throws Exception{
        Optional<User> userFound = userRepository.findByUsername(user.getUsername());
        if (userFound.isPresent()) {
            throw new Exception("El nombre de usuario no esta disponible");
        }
        return true;
    }
    
    private boolean checkPassword(User user) throws Exception{
        if(!user.getPassword().equals(user.getConfirmPassword())){
           throw new Exception("La contraseña y su confirmación no son iguales"); 
        }
        return true;
    }

    @Override
    public User createUser(User user) throws Exception {
        if(checkUsernameExists(user) && checkPassword(user)){
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            user = userRepository.save(user);
        }
        return user;
    }
    
}
