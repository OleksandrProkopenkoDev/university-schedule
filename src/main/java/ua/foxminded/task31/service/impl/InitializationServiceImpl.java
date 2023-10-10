package ua.foxminded.task31.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.foxminded.task31.dto.UniversityDataDto;
import ua.foxminded.task31.entity.*;
import ua.foxminded.task31.model.Schedule;
import ua.foxminded.task31.model.enums.Day;
import ua.foxminded.task31.model.enums.LessonNumber;
import ua.foxminded.task31.service.GenerationService;
import ua.foxminded.task31.service.InitializationService;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class InitializationServiceImpl implements InitializationService {

    public static final int CLASSROOMS_AMOUNT = 25;
    public static final int ADMINS_AMOUNT = 3;
    public static final int COURSES_AMOUNT = 20;
    public static final int TEACHERS_AMOUNT = 35;
    public static final int GROUPS_AMOUNT = 10;
    public static final int STUDENTS_AMOUNT = 200;

    private final Random random = new Random();

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
    public Schedule generateSchedule(UniversityDataDto universityDataDto) {
        Schedule schedule = new Schedule();
        List<Lesson> lessons = generationService.generateLessonsFrom(universityDataDto);
        List<Group> groups = universityDataDto.getGroups();

        //get one group
        groups.forEach(group -> {
            //get list of lessons for this group
            List<Lesson> lessonList = lessons.stream()
                    .filter(lesson -> lesson.getGroup().equals(group))
                    .collect(Collectors.toList());

            //go day by day
            for (Day day: Day.values()) {
                //select one lesson and set it to this day
                int num = random.nextInt(lessonList.size());
                Lesson lesson = lessonList.get(num);
                lesson.setDay(day);

                //need to set time
                int lessonNumber = random.nextInt(1,5);
                lesson.setLessonNumber(LessonNumber.fromValue(lessonNumber));

                //try to add new lesson with setted time to schedule
                 if(schedule.addLesson(lesson)){
                     lessonList.remove(lesson);
                 }
            }

        });
        return schedule;
    }

    @Override
    public void fillDbWithGeneratedData() {

    }
}
