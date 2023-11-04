package ua.foxminded.task31.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ua.foxminded.task31.model.entity.Course;
import ua.foxminded.task31.model.entity.Group;
import ua.foxminded.task31.model.entity.UserEntity;
import ua.foxminded.task31.service.CourseService;
import ua.foxminded.task31.service.GroupService;
import ua.foxminded.task31.service.UserService;

import java.util.List;

@Controller
@AllArgsConstructor
public class DataController {

    private final UserService userService;
    private final CourseService courseService;
    private final GroupService groupService;

    @GetMapping("/schedule")
    public String showSchedule(Model model){

        return "schedule";
    }

    @GetMapping("/users")
    public String showUsers(Model model){
        List<UserEntity> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/courses")
    public String showCourses(Model model){
        List<Course> courses = courseService.getAllCourses();
        model.addAttribute("courses", courses);
        return "courses";
    }

    @GetMapping("/groups")
    public String showGroups(Model model){
        List<Group> groups = groupService.getAllGroups();
        model.addAttribute("groups", groups);
        return "groups";
    }

    @GetMapping("/schedule-constructor")
    public String showScheduleConstructor(Model model){
        return "schedule-constructor";
    }
}
