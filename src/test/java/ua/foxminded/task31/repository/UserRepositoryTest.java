package ua.foxminded.task31.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ua.foxminded.task31.model.entity.Teacher;
import ua.foxminded.task31.model.entity.UserEntity;
import ua.foxminded.task31.model.enums.Role;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

    @Autowired
    private UserRepository underTest;

    @Test
    void findAllTeachers_shouldReturnList(){
        List<UserEntity> allTeachers = underTest.findAllByRole(Role.TEACHER);
        assertThat(allTeachers).isNotEmpty();
        allTeachers.forEach(System.out::println);
    }
}