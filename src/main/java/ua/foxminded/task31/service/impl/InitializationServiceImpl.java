package ua.foxminded.task31.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.foxminded.task31.dto.UniversityDataDto;
import ua.foxminded.task31.entity.*;
import ua.foxminded.task31.model.Position;
import ua.foxminded.task31.model.Schedule;
import ua.foxminded.task31.model.enums.Day;
import ua.foxminded.task31.model.enums.LessonNumber;
import ua.foxminded.task31.service.GenerationService;
import ua.foxminded.task31.service.InitializationService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class InitializationServiceImpl implements InitializationService {

    public static final int CLASSROOMS_AMOUNT = 35;
    public static final int ADMINS_AMOUNT = 3;
    public static final int COURSES_AMOUNT = 15;
    public static final int TEACHERS_AMOUNT = 45;
    public static final int GROUPS_AMOUNT = 10;
    public static final int STUDENTS_AMOUNT = 200;
    public static final int NUMBER_OF_LESSONS_PER_DAY = 4;

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

            for (int i = 0; i < NUMBER_OF_LESSONS_PER_DAY; i++) {

                //go day by day
                for (Day day : Day.values()) {
                    int attempts = 0;

                    while (attempts < 10) {
                        if(lessonList.isEmpty()){
                            break;
                        }
                        //select one lesson
                        int num = random.nextInt(lessonList.size());
                        Lesson lesson = lessonList.get(num);

                        //and set it to this day
                        lesson.setDay(day);

                        //need to set time
                        int lessonNumber = random.nextInt(1, 5);
                        lesson.setLessonNumber(LessonNumber.fromValue(lessonNumber));
                        Position currentPosition = new Position(day, LessonNumber.fromValue(lessonNumber));
                        //need to get lessons already existing for this day
                        Map<Position, Lesson> daySchedule = schedule.forDayByGroup(day, group);
                        if (daySchedule.get(currentPosition) != null) {
                            // lesson already exists on this position
                            shift(currentPosition);
                            while (checkGap(daySchedule, currentPosition)) {
                                shift(currentPosition);

                            }
                        }
                        //try to add new lesson with setted time to schedule
                        if (schedule.addLesson(lesson)) {
                            lessonList.remove(lesson);
                            break;
                        }else {
                            lesson.setDay(null);
                            lesson.setLessonNumber(null);
                            attempts++;
                        }
                    }
                }
            }

        });
        return schedule;
    }

    private boolean checkGap(Map<Position, Lesson> daySchedule, Position position) {
        int[] positions = new int[NUMBER_OF_LESSONS_PER_DAY+1];
        positions[position.getLessonNumber().getValue()] = position.getLessonNumber().getValue();
        daySchedule.forEach((pos, lesson) -> {
            positions[pos.getLessonNumber().getValue()] = pos.getLessonNumber().getValue();
        });
        int min = Arrays.stream(positions).min().getAsInt();
        long lessonsThisDay = Arrays.stream(positions)
                .filter(value -> value != 0)
                .count();
        int count = 0;
        for (int i = 0; i < lessonsThisDay - 1; i++) {
            if (i == min) {
                if (positions[i] == positions[i] + 1) {
                    count++;
                }
            }
        }
        return count == lessonsThisDay - 1;
    }

    private void shift(Position position) {
        LessonNumber lessonNumber = position.getLessonNumber();
        int value = lessonNumber.getValue();
        value++;
        if (value > 4) {
            value = 1;
        }
        position.setLessonNumber(LessonNumber.fromValue(value));
    }

    @Override
    public void fillDbWithGeneratedData() {

    }
}
