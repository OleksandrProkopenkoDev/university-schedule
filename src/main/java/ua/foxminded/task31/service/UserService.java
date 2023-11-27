package ua.foxminded.task31.service;

import ua.foxminded.task31.model.dto.SaveUserDto;
import ua.foxminded.task31.model.entity.Teacher;
import ua.foxminded.task31.model.entity.UserEntity;
import java.util.List;

public interface UserService {
    List<UserEntity> getAllUsers();

    List<Teacher> findAllTeachers();

    Teacher findTeacherById(Long teacherId);

    void saveUser(SaveUserDto userDto);

    void deleteUser(Long userId);

    SaveUserDto findById(Long userId);
}
