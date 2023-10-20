package ua.foxminded.task31.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.foxminded.task31.dataGeneration.impl.GenerationServiceImpl;
import ua.foxminded.task31.model.dto.UniversityDataDto;
import ua.foxminded.task31.entity.*;
import ua.foxminded.task31.dataGeneration.GenerationService;
import ua.foxminded.task31.model.entity.*;
import ua.foxminded.task31.service.InitializationService;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class GenerationServiceImplTest {

    private GenerationService underTest;
    private InitializationService initializationService;

    @BeforeEach
    void setUp() {
        underTest = new GenerationServiceImpl();
        initializationService = new InitializationServiceImpl(underTest);
    }

    @Test
    void _shouldGenerateStudents() {
        // Given
        int amount = 200;
        // When
        List<Student> students = underTest.generateStudents(amount);
        // Then
        students.forEach(System.out::println);
    }

    @Test
    void _shouldGenerateCourses() {
        // Given

        // When

        // Then
    }

    @Test
    void generateGroups_shouldGenerateGroupsUsingCourses() {
        // Given
        int groupsAmount = 10;
        int coursesAmount = 25;
        List<Course> courses = underTest.generateCourses(coursesAmount);
        // When
        List<Group> groups = underTest.generateGroups(groupsAmount, courses);
        // Then
        groups.forEach(System.out::println);
    }

    @Test
    void _shouldGenerateTeachers() {
        // Given
        int amount = 25;
        // When
        List<Teacher> teachers = underTest.generateTeachers(amount);
        // Then
        teachers.forEach(System.out::println);
    }

    @Test
    void _shouldGenerateAdmins() {
        // Given
        int amount = 3;
        // When
        List<Admin> admins = underTest.generateAdmins(amount);
        // Then
        admins.forEach(System.out::println);
    }

    @Test
    void generateClassrooms_shouldGenerateClassroomsByAmount() {
        // Given
        int amount = 20;
        // When
        List<Classroom> classrooms = underTest.generateClassrooms(amount);
        // Then
        assertThat(classrooms).hasSize(amount);
        classrooms.forEach(System.out::println);
    }

    @Test
    void generateClassrooms_shouldGenerateClassroomsByAmountAndCoursesList() {
        // Given
        int amount = 20;
        int coursesAmount = 25;
        // When
        List<Course> courses = underTest.generateCourses(coursesAmount);
        courses.forEach(System.out::println);
        List<Classroom> classrooms = underTest.generateClassrooms(amount, courses);
        // Then
        assertThat(classrooms).hasSize(amount);
        classrooms.forEach(System.out::println);
    }

    @Test
    void _shouldGenerateCurriculum() {
        // Given
        int groupsNumber = 20;
        int coursesNumber = 25;
        // When
        int[][] curriculumTable = underTest.generateCurriculumTable(groupsNumber, coursesNumber);
        // Then
        for (int i = 0; i < curriculumTable.length; i++) {
            int totalLessonsCount = 0;
            for (int j = 0; j < curriculumTable[i].length; j++) {
                System.out.print(curriculumTable[i][j] + " ");
                totalLessonsCount += curriculumTable[i][j];
            }
            System.out.println(" - " + totalLessonsCount);
        }
    }

    @Test
    void generateLessonsFromData() {
        UniversityDataDto universityDataDto = initializationService.generateInitialData();
        List<Lesson> lessons = underTest.generateLessonsFrom(universityDataDto);
//        lessons.forEach(System.out::println);
        Group group = lessons.get(0).getGroup();
        List<Lesson> lessonList = lessons.stream()
                .filter(lesson -> lesson.getGroup().equals(group))
                .collect(Collectors.toList());
        lessonList.forEach(System.out::println);
    }

}