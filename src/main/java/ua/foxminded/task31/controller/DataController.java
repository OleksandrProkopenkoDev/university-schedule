package ua.foxminded.task31.controller;

import jakarta.persistence.MapsId;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.foxminded.task31.model.dto.SaveUserDto;
import ua.foxminded.task31.model.entity.Course;
import ua.foxminded.task31.model.entity.Group;
import ua.foxminded.task31.model.entity.UserEntity;
import ua.foxminded.task31.model.enums.Role;
import ua.foxminded.task31.service.CourseService;
import ua.foxminded.task31.service.GroupService;
import ua.foxminded.task31.service.ScheduleService;
import ua.foxminded.task31.service.UserService;

import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@AllArgsConstructor
public class DataController {

    private final UserService userService;
    private final CourseService courseService;
    private final GroupService groupService;
    private final ScheduleService scheduleService;

    @GetMapping("/schedule")
    public String showSchedule(Model model, @RequestParam(required = false) Map<String, String> params) {
        scheduleService.prepareSchedule(model, params);
        return "schedule";
    }

    @GetMapping("/users")
    public String showUsers(Model model) {
        List<UserEntity> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/users/add-new")
    public String showSaveUserPage(Model model) {

        model.addAttribute("user", new SaveUserDto());

        return "users/save-user";
    }

    @PostMapping("/users/add-new")
    public String saveUser(@ModelAttribute("user") SaveUserDto userDto) {
        userService.saveUser(userDto);
        return "redirect:/users";
    }

    @GetMapping("/users/delete-user/{userId}")
    public String deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return "redirect:/users";
    }

    @GetMapping("/courses")
    public String showCourses(Model model) {
        List<Course> courses = courseService.findAllCourses();
        model.addAttribute("courses", courses);
        return "courses";
    }

    @GetMapping("/groups")
    public String showGroups(Model model) {
        List<Group> groups = groupService.findAllGroups();
        model.addAttribute("groups", groups);
        return "groups";
    }

    @GetMapping("/schedule-constructor")
    public String showScheduleConstructor(Model model) {
        return "schedule-constructor";
    }

    @ModelAttribute("courses")
    public List<Course> getCourses(){
        return courseService.findAllCourses();
    }

    @ModelAttribute("groups")
    public List<Group> getGroups(){
        return groupService.findAllGroups();
    }

    @ModelAttribute("roles")
    public Role[] getRoles(){
        return Role.values();
    }

}
