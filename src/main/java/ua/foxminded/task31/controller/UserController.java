package ua.foxminded.task31.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ua.foxminded.task31.model.entity.UserEntity;
import ua.foxminded.task31.service.UserService;

import java.util.List;

@Controller
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public String showUsers(Model model){
        List<UserEntity> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }
}
