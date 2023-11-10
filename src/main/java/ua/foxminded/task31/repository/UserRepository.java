package ua.foxminded.task31.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.foxminded.task31.model.entity.Teacher;
import ua.foxminded.task31.model.entity.UserEntity;
import ua.foxminded.task31.model.enums.Role;

import java.util.List;
import java.util.stream.Collectors;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("SELECT u FROM UserEntity u WHERE u.role = :role")
    List<UserEntity> findAllByRole(@Param("role") Role role);

}
