package ua.foxminded.task31.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.foxminded.task31.exception.DaoException;
import ua.foxminded.task31.model.dto.SaveUserDto;
import ua.foxminded.task31.model.entity.Admin;
import ua.foxminded.task31.model.entity.Student;
import ua.foxminded.task31.model.entity.Teacher;
import ua.foxminded.task31.model.entity.UserEntity;
import ua.foxminded.task31.model.enums.Role;
import ua.foxminded.task31.repository.AdminRepository;
import ua.foxminded.task31.repository.StudentRepository;
import ua.foxminded.task31.repository.TeacherRepository;
import ua.foxminded.task31.repository.UserRepository;
import ua.foxminded.task31.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final AdminRepository adminRepository;

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

    @Override
    public void saveUser(SaveUserDto userDto) {
        if(userDto.getRole().equals(Role.STUDENT)){
            studentRepository.save(mapUserDtoToStudent(userDto));
        } else if (userDto.getRole().equals(Role.TEACHER)) {
            teacherRepository.save(mapUserDtoToTeacher(userDto));
        }else {
            adminRepository.save(mapUserDtoToAdmin(userDto));
        }
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    private Admin mapUserDtoToAdmin(SaveUserDto userDto) {
        return new Admin(
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmail(),
                userDto.getPassword(),
                userDto.getRole()
        );
    }

    private Teacher mapUserDtoToTeacher(SaveUserDto userDto) {
        return new Teacher(
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmail(),
                userDto.getPassword(),
                userDto.getRole(),
                userDto.getCourse()
        );
    }

    private Student mapUserDtoToStudent(SaveUserDto userDto) {
        return new Student(
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmail(),
                userDto.getPassword(),
                userDto.getRole(),
                userDto.getGroup()
                );
    }
}
