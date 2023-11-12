package ua.foxminded.task31.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.foxminded.task31.model.entity.Course;
import ua.foxminded.task31.model.entity.Group;
import ua.foxminded.task31.model.entity.Lesson;
import ua.foxminded.task31.model.entity.Teacher;
import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

    List<Lesson> findAllByTeacher(Teacher teacher);

    List<Lesson> findAllByTeacherAndCourseAndGroup(Teacher teacher, Course course, Group group);

    List<Lesson> findByGroupAndTeacherAndCourse(Group group, Teacher teacher, Course course);

    List<Lesson> findByGroupAndTeacher(Group group, Teacher teacher);

    List<Lesson> findByGroupAndCourse(Group group, Course course);

    List<Lesson> findByTeacherAndCourse(Teacher teacher, Course course);

    List<Lesson> findByGroup(Group group);

    List<Lesson> findByTeacher(Teacher teacher);

    List<Lesson> findByCourse(Course course);
}
