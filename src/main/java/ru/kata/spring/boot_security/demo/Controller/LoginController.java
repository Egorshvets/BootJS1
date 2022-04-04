package ru.kata.spring.boot_security.demo.Controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.Service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/")
    public String welcome(Principal user) {
        if (user != null) {
            return "redirect:/auth/success";
        }
        return "welcome";
    }

    @GetMapping("/auth/login")
    public String login() {
        return "login";
    }

    @GetMapping("/auth/success")
    public String success(Principal user, ModelMap modelMap, Authentication authentication) {
        if (AuthorityUtils.authorityListToSet(authentication.getAuthorities()).contains("users:write")) {
            return "redirect:/admin/users/";
        }
        modelMap.addAttribute("user", userService.getUserByUsername(user.getName()));
        return "success";
    }

}
