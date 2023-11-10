package ua.foxminded.task31.service;

import ua.foxminded.task31.model.entity.Teacher;
import ua.foxminded.task31.model.entity.UserEntity;
import java.util.List;

public interface UserService {
    List<UserEntity> getAllUsers();

    List<Teacher> findAllTeachers();

    Teacher findTeacherById(Long teacherId);
}
