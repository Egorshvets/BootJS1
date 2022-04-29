package ru.kata.spring.boot_security.demo.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.DAO.UserDAO;
import ru.kata.spring.boot_security.demo.Model.User;
import ru.kata.spring.boot_security.demo.Service.UserService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class FirstRestController {

    final UserService userService;

    public FirstRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable int id) {
       return userService.getUserById(id);
    }

    @PatchMapping(value ="/users", consumes = {"*/*"})
    public User updateUser(@RequestBody User user) {
        String role = user.getStringRole();
        if (role.equals("[USER]")) {
            role = "USER";
        } else { role = "ADMIN"; }
        user.setRoles(userService.getRoleByName(role));
        userService.updateUser(user);
        return user;
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable int id) {
        userService.deleteUserById(id);
        return "User with id " + id + " was deleted";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    public User newUser(@RequestBody User user) {
        String role = user.getStringRole();
        if (role.equals("[USER]")) {
            role = "USER";
        } else { role = "ADMIN"; }
        user.setRoles(userService.getRoleByName(role));
        userService.addUser(user);

        return user;
    }

}
