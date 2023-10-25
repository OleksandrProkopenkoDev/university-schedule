package ua.foxminded.task31.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.foxminded.task31.model.entity.UserEntity;
import ua.foxminded.task31.repository.UserRepository;
import ua.foxminded.task31.service.UserService;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }
}
