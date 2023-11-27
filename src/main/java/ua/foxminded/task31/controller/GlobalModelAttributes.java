package ua.foxminded.task31.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import ua.foxminded.task31.model.entity.Course;
import ua.foxminded.task31.model.entity.Group;
import ua.foxminded.task31.model.enums.Role;
import ua.foxminded.task31.service.CourseService;
import ua.foxminded.task31.service.GroupService;

import java.util.List;

@AllArgsConstructor
@ControllerAdvice
public class GlobalModelAttributes {

    private final CourseService courseService;
    private final GroupService groupService;

    @ModelAttribute("courses")
    public List<Course> getCourses() {
        return courseService.findAllCourses();
    }

    @ModelAttribute("groups")
    public List<Group> getGroups() {
        return groupService.findAllGroups();
    }

    @ModelAttribute("roles")
    public Role[] getRoles() {
        return Role.values();
    }
}
