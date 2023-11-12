package ua.foxminded.task31.service.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.foxminded.task31.model.Schedule;
import ua.foxminded.task31.model.entity.Course;
import ua.foxminded.task31.model.entity.Group;
import ua.foxminded.task31.model.entity.Lesson;
import ua.foxminded.task31.model.entity.Teacher;
import ua.foxminded.task31.repository.LessonRepository;
import ua.foxminded.task31.service.ScheduleService;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private LessonRepository lessonRepository;

    @Override
    public Schedule getUniversitySchedule() {
        List<Lesson> lessons = lessonRepository.findAll();
        return new Schedule(lessons);
    }

    @Override
    public Schedule getTeacherSchedule(Teacher teacher) {
        List<Lesson> lessonsByTeacher = lessonRepository.findAllByTeacher(teacher);
        return new Schedule(lessonsByTeacher);
    }

    @Override
    public Schedule getScheduleByTeacherAndCourseAndGroup(Teacher teacher, Course course, Group group) {
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
}
