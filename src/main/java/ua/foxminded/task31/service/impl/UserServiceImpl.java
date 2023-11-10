package ua.foxminded.task31.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.foxminded.task31.exception.DaoException;
import ua.foxminded.task31.model.entity.Teacher;
import ua.foxminded.task31.model.entity.UserEntity;
import ua.foxminded.task31.model.enums.Role;
import ua.foxminded.task31.repository.UserRepository;
import ua.foxminded.task31.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<Teacher> findAllTeachers() {
        List<UserEntity> userEntities = userRepository.findAllByRole(Role.TEACHER);
        return userEntities.stream()
                .map(userEntity -> (Teacher) userEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Teacher findTeacherById(Long teacherId) {
        return (Teacher) userRepository.findById(teacherId)
                .orElseThrow(() -> new DaoException(format("Teacher with id [%s] is not found", teacherId)));

    }
}
