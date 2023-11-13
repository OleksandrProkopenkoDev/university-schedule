package ua.foxminded.task31.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import ua.foxminded.task31.model.Schedule;
import ua.foxminded.task31.model.entity.Course;
import ua.foxminded.task31.model.entity.Group;
import ua.foxminded.task31.model.entity.Teacher;

import java.util.Map;

public interface ScheduleService {
    Schedule getUniversitySchedule();

    Schedule getTeacherSchedule(Teacher teacher);

    Schedule getScheduleByTeacherAndCourseAndGroup(Teacher teacher, Course course, Group group);

    void prepareSchedule(Model model, Map<String, String> params);
}
