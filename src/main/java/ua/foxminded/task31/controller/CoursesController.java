package ua.foxminded.task31.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.foxminded.task31.model.dto.CourseDto;
import ua.foxminded.task31.model.entity.Course;
import ua.foxminded.task31.service.CourseService;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Controller
@RequestMapping("/courses")
public class CoursesController {

    private final CourseService courseService;

    @GetMapping
    public String showCourses(Model model) {
        List<Course> courses = courseService.findAllCourses();
        model.addAttribute("courses", courses);
        return "courses";
    }

    @GetMapping("/add-new")
    public String showSaveCoursePage(Model model) {
        if (model.getAttribute("course") == null) {
            model.addAttribute("course", new CourseDto());
        }
        return "courses/save-course";
    }

    @PostMapping("/add-new")
    public String saveCourse(@Valid @ModelAttribute("course") CourseDto courseDto, BindingResult result) {
        if (result.hasErrors()) {
            return "courses/save-course"; // Return to the form page with validation errors
        }
        // Process the user data if validation passes
        courseService.saveCourse(courseDto);
        return "redirect:/courses"; // Redirect to the user list page
    }

    @GetMapping("/update")
    public String updateUser(@RequestParam("courseId") Long userId, Model model) {
        CourseDto courseDto = courseService.findById(userId);
        model.addAttribute("course", courseDto);
        return "courses/save-course";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam("courseId") Long courseId) {
        courseService.deleteCourse(courseId);
        return "redirect:/courses";
    }

}
