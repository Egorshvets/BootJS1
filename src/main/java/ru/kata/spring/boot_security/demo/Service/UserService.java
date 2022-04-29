package ru.kata.spring.boot_security.demo.Service;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.Model.Role;
import ru.kata.spring.boot_security.demo.Model.User;

import java.util.List;

public interface UserService extends UserDetailsService {

    public List<User> getAllUsers();
    public User getUserById(int Id);
    public void deleteUserById(int Id);
    public void updateUser(int Id, String userName, String email, int age, String role,String password);
    public void updateUser(User user);
    public void addUser(User user);
    public User getUserByUsername(String username);
    public UserDetails loadUserByUsername(String username);
    public List<Role> getAllRoles();
    public Role getRoleByName(String role);
}
