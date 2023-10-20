package ua.foxminded.task31.dataGeneration;

import ua.foxminded.task31.model.dto.UniversityDataDto;
import ua.foxminded.task31.model.entity.*;
import ua.foxminded.task31.model.entity.*;

import java.util.List;

public interface GenerationService {

    List<Student> generateStudents(int amount);
    List<Student> generateStudents(int amount, List<Group> groups);
    List<Course> generateCourses(int amount);
    List<Group> generateGroups(int amount);
    List<Group> generateGroups(int amount, List<Course> courses);
    List<Group> generateGroups(int amount, List<Course> courses, int[][] curriculumTable);
    List<Teacher> generateTeachers(int amount);
    List<Teacher> generateTeachers(int amount, List<Course> courses);
    List<Admin> generateAdmins(int amount);
    List<Classroom> generateClassrooms(int amount);
    List<Classroom> generateClassrooms(int amount, List<Course> courses);
    int[][] generateCurriculumTable(int groupsNumber, int coursesNumber);
    List<Lesson> generateLessonsFrom(UniversityDataDto universityDataDto);

}
