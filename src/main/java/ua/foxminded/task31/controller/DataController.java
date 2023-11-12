package ua.foxminded.task31.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.foxminded.task31.model.Schedule;
import ua.foxminded.task31.model.entity.Course;
import ua.foxminded.task31.model.entity.Group;
import ua.foxminded.task31.model.entity.Teacher;
import ua.foxminded.task31.model.entity.UserEntity;
import ua.foxminded.task31.model.enums.Day;
import ua.foxminded.task31.model.enums.LessonNumber;
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
        List<Teacher> teachers = userService.findAllTeachers();
        List<Group> groups = groupService.findAllGroups();
        List<Course> courses = courseService.findAllCourses();

        Group currentGroup = getGroup(params, groups);
        Course currentCourse = getCourse(params, courses);
        Teacher currentTeacher = getTeacher(params, teachers);

        Schedule universitySchedule = scheduleService
                .getScheduleByTeacherAndCourseAndGroup(currentTeacher, currentCourse, currentGroup);
        boolean showGroup = true, showClassroom = true, showCourse = true, showTeacher = true;
        model.addAttribute("teacherId", params.get("teacherId"));
        model.addAttribute("courseId", params.get("courseId"));
        model.addAttribute("groupId", params.get("groupId"));
        model.addAttribute("universitySchedule", universitySchedule);
        model.addAttribute("days", Day.values());
        model.addAttribute("lessonNumbers", LessonNumber.values());
        model.addAttribute("groups", groups);
        model.addAttribute("courses", courses);
        model.addAttribute("teachers", teachers);
        model.addAttribute("currentGroup", currentGroup);
        model.addAttribute("currentCourse", currentCourse);
        model.addAttribute("currentTeacher", currentTeacher);
        model.addAttribute("showGroup", showGroup);
        model.addAttribute("showClassroom", showClassroom);
        model.addAttribute("showCourse", showCourse);
        model.addAttribute("showTeacher", showTeacher);
        return "schedule";
    }


    @GetMapping("/users")
    public String showUsers(Model model) {
        List<UserEntity> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
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

    private Teacher getTeacher(Map<String, String> params, List<Teacher> teachers) {
        Teacher currentTeacher;
        String teacherIdStr = params.get("teacherId");
        if (teacherIdStr == null) {
            currentTeacher = new Teacher();
        } else {
            Long teacherId = Long.parseLong(teacherIdStr);
            currentTeacher = teachers.stream()
                    .filter(teacher -> teacher.getId().equals(teacherId))
                    .findFirst().orElse(new Teacher());
        }
        return currentTeacher;
    }

    private Group getGroup(Map<String, String> params, List<Group> groups) {
        Group currentGroup;
        String groupIdStr = params.get("groupId");
        if (groupIdStr == null) {
            currentGroup = new Group();
        } else {
            Long groupId = Long.parseLong(groupIdStr);
            currentGroup = groups.stream()
                    .filter(group -> group.getId().equals(groupId))
                    .findFirst().orElse(new Group());
        }
        return currentGroup;
    }

    private Course getCourse(Map<String, String> params, List<Course> courses) {
        Course currentCourse;
        String courseIdStr = params.get("courseId");
        if (courseIdStr == null) {
            currentCourse = new Course();
        } else {
            Long courseId = Long.parseLong(courseIdStr);
            currentCourse = courses.stream()
                    .filter(course -> course.getId().equals(courseId))
                    .findFirst().orElse(new Course());
        }
        return currentCourse;
    }
}
