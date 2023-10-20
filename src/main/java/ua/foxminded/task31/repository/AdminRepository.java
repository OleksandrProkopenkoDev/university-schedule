package ua.foxminded.task31.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.foxminded.task31.model.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
