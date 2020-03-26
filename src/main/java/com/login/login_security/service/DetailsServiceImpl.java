package com.login.login_security.service;
import com.login.login_security.model.Role;
import java.util.HashSet;
import java.util.Set;

import com.login.login_security.repository.UserRepository;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class DetailsServiceImpl implements UserDetailsService  {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.login.login_security.model.User appUser = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Error al iniciar sesión"));
        
        Set grantList = new HashSet();
        for(Role roles: appUser.getRoles()){
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(roles.getDescription());
            grantList.add(grantedAuthority);
        }
        UserDetails user = (UserDetails) new User(username,appUser.getPassword(),grantList);
        return user;
    }
    
}
