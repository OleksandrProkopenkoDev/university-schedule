package ua.foxminded.task31.service;

import ua.foxminded.task31.entity.*;

import java.util.List;

public interface GenerationService {

    List<Student> generateStudents(int amount);
    List<Course> generateCourses(int amount);
    List<Group> generateGroups(int amount);
    List<Teacher> generateTeachers(int amount);
    List<Classroom> generateClassrooms(int amount);
    List<Curriculum> generateCurriculum(int groupsNumber, int coursesNumber);


}
