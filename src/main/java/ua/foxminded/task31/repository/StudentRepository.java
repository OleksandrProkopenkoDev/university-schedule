package ua.foxminded.task31.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.foxminded.task31.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
