package ua.foxminded.task31.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.foxminded.task31.entity.Classroom;

public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
}
