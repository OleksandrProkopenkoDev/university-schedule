package ua.foxminded.task31.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import ua.foxminded.task31.model.Schedule;
import ua.foxminded.task31.model.entity.Course;
import ua.foxminded.task31.model.entity.Group;
import ua.foxminded.task31.model.entity.Lesson;
import ua.foxminded.task31.model.entity.Teacher;
import ua.foxminded.task31.model.enums.Day;
import ua.foxminded.task31.model.enums.LessonNumber;
import ua.foxminded.task31.repository.LessonRepository;
import ua.foxminded.task31.service.CourseService;
import ua.foxminded.task31.service.GroupService;
import ua.foxminded.task31.service.ScheduleService;
import ua.foxminded.task31.service.UserService;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private LessonRepository lessonRepository;
    private final UserService userService;
    private final CourseService courseService;
    private final GroupService groupService;

    @Override
    public void prepareSchedule(Model model, Map<String, String> params) {
        List<Teacher> teachers = userService.findAllTeachers();
        List<Group> groups = groupService.findAllGroups();
        List<Course> courses = courseService.findAllCourses();

        Group currentGroup = getGroup(params, groups);
        Course currentCourse = getCourse(params, courses);
        Teacher currentTeacher = getTeacher(params, teachers);

        Schedule universitySchedule =
                getScheduleByTeacherAndCourseAndGroup(currentTeacher, currentCourse, currentGroup);

        setDefaultCheckboxes(params);

        model.addAttribute("showGroup", params.get("showGroup"));
        model.addAttribute("showCourse", params.get("showCourse"));
        model.addAttribute("showClassroom", params.get("showClassroom"));
        model.addAttribute("showTeacher", params.get("showTeacher"));
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
    }

    private Schedule getUniversitySchedule() {
        List<Lesson> lessons = lessonRepository.findAll();
        return new Schedule(lessons);
    }


    private Schedule getTeacherSchedule(Teacher teacher) {
        List<Lesson> lessonsByTeacher = lessonRepository.findAllByTeacher(teacher);
        return new Schedule(lessonsByTeacher);
    }


    private Schedule getScheduleByTeacherAndCourseAndGroup(Teacher teacher, Course course, Group group) {
        List<Lesson> lessons;
        if (group.getId() != null && teacher.getId() != null && course.getId() != null) {
            lessons = lessonRepository.findByGroupAndTeacherAndCourse(group, teacher, course);
        } else if (group.getId() != null && teacher.getId() != null) {
            lessons = lessonRepository.findByGroupAndTeacher(group, teacher);
        } else if (group.getId() != null && course.getId() != null) {
            lessons = lessonRepository.findByGroupAndCourse(group, course);
        } else if (teacher.getId() != null && course.getId() != null) {
            lessons = lessonRepository.findByTeacherAndCourse(teacher, course);
        } else if (group.getId() != null) {
            lessons = lessonRepository.findByGroup(group);
        } else if (teacher.getId() != null) {
            lessons = lessonRepository.findByTeacher(teacher);
        } else if (course.getId() != null) {
            lessons = lessonRepository.findByCourse(course);
        } else {
            lessons = lessonRepository.findAll();
        }
        return new Schedule(lessons);
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

    private void setDefaultCheckboxes(Map<String, String> params) {
        params.putIfAbsent("showGroup", "true");
        params.putIfAbsent("showCourse", "true");
        params.putIfAbsent("showClassroom", "true");
        params.putIfAbsent("showTeacher", "true");
    }
}
