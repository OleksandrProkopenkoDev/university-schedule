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
    @Transactional
    public Schedule getScheduleByTeacherAndCourseAndGroup(Teacher teacher, Course course, Group group) {
        if(teacher.getId() == null){
            teacher = null;
        }
        if(course.getId() == null){
            course = null;
        }
        if(group.getId() == null){
            group = null;
        }
        List<Lesson> lessons = lessonRepository.findAllByTeacherAndCourseAndGroup(teacher, course, group);
        return new Schedule(lessons);
    }
}
