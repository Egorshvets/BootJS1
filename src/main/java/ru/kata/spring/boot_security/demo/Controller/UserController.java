package ru.kata.spring.boot_security.demo.Controller;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.Model.Role;
import ru.kata.spring.boot_security.demo.Model.User;
import ru.kata.spring.boot_security.demo.Service.UserService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/users/")
public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String showAllUsers(Principal currentUser, ModelMap model) {
        if(userService.getAllUsers().isEmpty()) {
            return "zeroPage";
        } else {
            List<User> users = new ArrayList<>(userService.getAllUsers());
            List<Role> roles = new ArrayList<>(userService.getAllRoles());
            model.addAttribute("currentUser", userService.getUserByUsername(currentUser.getName()));
            model.addAttribute("users", users);
            model.addAttribute("newUser", new User());
            model.addAttribute("allRoles", roles);
            return "UsersView";
        }
    }

    @GetMapping("secondRole")
    public String secondRole(Principal user, ModelMap modelMap) {
        modelMap.addAttribute("user", userService.getUserByUsername(user.getName()));
        return "success";
    }


    @PostMapping("save")
    public String saveNewUser(@ModelAttribute("newUser") User user, @RequestParam("role") String role) {
        user.setRoles(userService.getRoleByName(role));
        userService.addUser(user);
        return "redirect:/admin/users/";
    }

    @PostMapping("delete")
    public String deleteUser(@RequestParam("Id") String id) {
        userService.deleteUserById(Integer.parseInt(id));
        return "redirect:/admin/users/";
    }


    @PostMapping("updating")
    public String updatingUser(@RequestParam("name") String userName, @RequestParam("email") String email, @RequestParam("age") int age, @RequestParam("Id") int id, @RequestParam("role") String role, @RequestParam("password") String password) {
        userService.updateUser(id, userName, email, age, role, password);
        return "redirect:/admin/users/";
    }
}
