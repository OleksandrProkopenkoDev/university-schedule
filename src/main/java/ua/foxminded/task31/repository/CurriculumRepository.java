package ua.foxminded.task31.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.foxminded.task31.entity.Curriculum;

public interface CurriculumRepository extends JpaRepository<Curriculum, Long> {
}
