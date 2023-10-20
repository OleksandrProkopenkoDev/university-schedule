package ua.foxminded.task31.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.foxminded.task31.model.entity.Lesson;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
}
