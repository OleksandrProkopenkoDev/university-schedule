package ua.foxminded.task31.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.foxminded.task31.model.entity.Group;

public interface GroupRepository extends JpaRepository<Group, Long> {
}
