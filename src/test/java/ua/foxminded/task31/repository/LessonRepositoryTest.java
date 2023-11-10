package ua.foxminded.task31.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ua.foxminded.task31.model.entity.Lesson;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class LessonRepositoryTest {

    @Autowired
    private LessonRepository underTest;

    @Test
    void findByGroupLessonCourse_shouldReturnAllLessons(){
        List<Lesson> lessons = underTest.findAllByTeacherAndCourseAndGroup(null, null, null);
        assertThat(lessons).isNotEmpty();
        lessons.forEach(System.out::println);
    }
}