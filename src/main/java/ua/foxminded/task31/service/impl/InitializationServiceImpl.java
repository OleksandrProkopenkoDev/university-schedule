package ua.foxminded.task31.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.foxminded.task31.dto.UniversityDataDto;
import ua.foxminded.task31.entity.*;
import ua.foxminded.task31.service.GenerationService;
import ua.foxminded.task31.service.InitializationService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class InitializationServiceImpl implements InitializationService {

    public static final int CLASSROOMS_AMOUNT = 20;
    public static final int ADMINS_AMOUNT = 3;
    public static final int COURSES_AMOUNT = 25;
    public static final int TEACHERS_AMOUNT = 20;
    public static final int GROUPS_AMOUNT = 10;
    public static final int STUDENTS_AMOUNT = 200;
    private final GenerationService generationService;

    @Override
    public UniversityDataDto generateInitialData() {
        List<Admin> admins = generationService.generateAdmins(ADMINS_AMOUNT);
        List<Course> courses = generationService.generateCourses(COURSES_AMOUNT);
        List<Classroom> classrooms = generationService.generateClassrooms(CLASSROOMS_AMOUNT, courses);
        List<Teacher> teachers = generationService.generateTeachers(TEACHERS_AMOUNT, courses);
        int[][] curriculumTable = generationService.generateCurriculumTable(GROUPS_AMOUNT, COURSES_AMOUNT);
        List<Group> groups = generationService.generateGroups(GROUPS_AMOUNT, courses, curriculumTable);
        List<Student> students = generationService.generateStudents(STUDENTS_AMOUNT, groups);
       return new UniversityDataDto(
               admins,
               courses,
               classrooms,
               teachers,
               curriculumTable,
               groups,
               students
       );
    }

    @Override
    public void generateSchedule() {

    }

    @Override
    public void fillDbWithGeneratedData() {

    }
}
