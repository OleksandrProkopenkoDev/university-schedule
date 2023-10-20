package ua.foxminded.task31.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.foxminded.task31.model.entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
