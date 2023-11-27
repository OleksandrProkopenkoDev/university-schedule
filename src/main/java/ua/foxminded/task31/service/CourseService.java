package ua.foxminded.task31.service;

import ua.foxminded.task31.model.dto.CourseDto;
import ua.foxminded.task31.model.entity.Course;

import java.util.List;

public interface CourseService {
    List<Course> findAllCourses();

    void saveCourse(CourseDto courseDto);

    CourseDto findById(Long courseId);

    void deleteCourse(Long courseId);
}
