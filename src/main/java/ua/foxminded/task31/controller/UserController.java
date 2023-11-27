package ua.foxminded.task31.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.foxminded.task31.model.dto.SaveUserDto;
import ua.foxminded.task31.model.entity.UserEntity;
import ua.foxminded.task31.service.UserService;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public String showUsers(Model model) {
        List<UserEntity> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/add-new")
    public String showSaveUserPage(Model model) {
            if(model.getAttribute("user") == null) {
                model.addAttribute("user", new SaveUserDto());
            }
        return "users/save-user";
    }

    @PostMapping("/add-new")
    public String saveUser(@Valid @ModelAttribute("user") SaveUserDto userDto, BindingResult result) {
        if (result.hasErrors()) {
            return "users/save-user"; // Return to the form page with validation errors
        }
        // Process the user data if validation passes
        userService.saveUser(userDto);
        return "redirect:/users"; // Redirect to the user list page
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam("userId") Long userId) {
        userService.deleteUser(userId);
        return "redirect:/users";
    }

    @GetMapping("/update")
    public String updateUser(@RequestParam("userId") Long userId, Model model) {
        SaveUserDto userEntity = userService.findById(userId);
        model.addAttribute("user", userEntity);
        return "users/save-user";
    }

}
