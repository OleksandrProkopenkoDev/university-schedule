package ua.foxminded.task31.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.foxminded.task31.model.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
