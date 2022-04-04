package ru.kata.spring.boot_security.demo.Service;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.Model.User;

import java.util.List;

public interface UserService extends UserDetailsService {

    public List<User> getAllUsers();
    public void addUser(String userName, String email, int age);
    public User getUserById(int Id);
    public void deleteUserById(int Id);
    public void updateUser(int Id, String userName, String email, int age);
    public void updateUser(User user);
    public void addUser(User user);
    public User getUserByUsername(String username);
    public UserDetails loadUserByUsername(String username);
}
