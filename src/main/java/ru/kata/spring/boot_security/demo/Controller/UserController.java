package ru.kata.spring.boot_security.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.Model.User;
import ru.kata.spring.boot_security.demo.Service.UserService;

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
    public String showAllUsers(ModelMap model) {
        if(userService.getAllUsers().isEmpty()) {
            return "zeroPage";
        } else {
            List<User> users = new ArrayList<>();
            for(User user: userService.getAllUsers()) {
                users.add(user);
            }
            model.addAttribute("users", users);
            return "UsersView";
        }
    }

    @GetMapping("add")
    public String addUser(ModelMap modelMap) {
        modelMap.addAttribute("user", new User());
        return "addUser";
    }

    @PostMapping("save")
    public String saveNewUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/users/";
    }

    @PostMapping("delete")
    public String deleteUser(@RequestParam("Id") String id) {
        userService.deleteUserById(Integer.parseInt(id));
        return "redirect:/users/";
    }

    @PostMapping("update")
    public String updateUser(@RequestParam("user") String Id, ModelMap model) {
        model.addAttribute("user", userService.getUserById(Integer.parseInt(Id)));
        return "updateUser";
    }

    @PostMapping("updating")
    public String updatingUser(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/users/";
    }
}
