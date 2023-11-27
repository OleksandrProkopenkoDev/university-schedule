package ua.foxminded.task31.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.foxminded.task31.exception.DaoException;
import ua.foxminded.task31.model.dto.CourseDto;
import ua.foxminded.task31.model.entity.Course;
import ua.foxminded.task31.repository.CourseRepository;
import ua.foxminded.task31.service.CourseService;

import java.util.List;

import static java.lang.String.format;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Override
    public List<Course> findAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public void saveCourse(CourseDto courseDto) {
        courseRepository.save(mapToCourse(courseDto));
    }

    @Override
    public CourseDto findById(Long courseId) {
        Course course = courseRepository
                .findById(courseId)
                .orElseThrow(
                        () -> new DaoException(format("Course with id [%s] is not found", courseId))
                );

        return mapCourseToDto(course);
    }

    @Override
    public void deleteCourse(Long courseId) {
        courseRepository.deleteById(courseId);
    }

    private CourseDto mapCourseToDto(Course course) {
        return new CourseDto(
                course.getId(),
                course.getName(),
                course.getDescription()
        );
    }

    private Course mapToCourse(CourseDto courseDto) {
        return new Course(
                courseDto.getId(),
                courseDto.getName(),
                courseDto.getDescription()
        );
    }
}
