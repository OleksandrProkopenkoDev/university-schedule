package ua.foxminded.task31.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.foxminded.task31.model.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
