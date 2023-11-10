package ua.foxminded.task31.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.foxminded.task31.dataGeneration.GenerationService;
import ua.foxminded.task31.model.Position;
import ua.foxminded.task31.model.Schedule;
import ua.foxminded.task31.model.dto.UniversityDataDto;
import ua.foxminded.task31.model.entity.*;
import ua.foxminded.task31.model.enums.Day;
import ua.foxminded.task31.model.enums.LessonNumber;
import ua.foxminded.task31.repository.*;
import ua.foxminded.task31.service.InitializationService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
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

    private final AdminRepository adminRepository;

    private final ClassroomRepository classroomRepository;

    private final CourseRepository courseRepository;

    private final GroupRepository groupRepository;

    private final LessonRepository lessonRepository;

    private final StudentRepository studentRepository;

    private final TeacherRepository teacherRepository;



    @Override
    public UniversityDataDto generateInitialData() {
        List<Admin> admins = generationService.generateAdmins(ADMINS_AMOUNT);
        log.debug("Generated {} Admins", ADMINS_AMOUNT);

        List<Course> courses = generationService.generateCourses(COURSES_AMOUNT);
        log.debug("Generated {} Courses", COURSES_AMOUNT);

        List<Classroom> classrooms = generationService.generateClassrooms(CLASSROOMS_AMOUNT, courses);
        log.debug("Generated {} Classrooms", CLASSROOMS_AMOUNT);

        List<Teacher> teachers = generationService.generateTeachers(TEACHERS_AMOUNT, courses);
        log.debug("Generated {} Teachers", TEACHERS_AMOUNT);

        int[][] curriculumTable = generationService.generateCurriculumTable(GROUPS_AMOUNT, COURSES_AMOUNT);
        log.debug("Generated curriculum table for {} groups and {} courses", GROUPS_AMOUNT, COURSES_AMOUNT);

        List<Group> groups = generationService.generateGroups(GROUPS_AMOUNT, courses, curriculumTable);
        log.debug("Generated {} Groups", GROUPS_AMOUNT);

        List<Student> students = generationService.generateStudents(STUDENTS_AMOUNT, groups);
        log.debug("Generated {} Students", STUDENTS_AMOUNT);

        log.info("Data Generation completed");
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
        log.trace("generation schedule method started");
        Schedule schedule = new Schedule();
        List<Lesson> lessons = generationService.generateLessonsFrom(universityDataDto);
        List<Group> groups = universityDataDto.getGroups();

        //get one group
        groups.forEach(group -> {
            log.trace(group + " is now processing");
            //get list of lessons for this group
            List<Lesson> lessonList = lessons.stream()
                    .filter(lesson -> lesson.getGroup().equals(group))
                    .collect(Collectors.toList());
            for (int i = 0; i < NUMBER_OF_LESSONS_PER_DAY; i++) {
                log.trace("lessonList for {} has size : " + lessonList.size(), group.getName());
                log.trace("tracing the [{}] lesson of the day", i + 1);
                //go day by day
                for (Day day : Day.values()) {
                    log.trace("Day = " + day);
                    int attempt = 0;

                    while (attempt < lessonList.size()) {
                        if (lessonList.isEmpty()) {
                            break;
                        }
                        //select one lesson

                        Lesson lesson = lessonList.get(attempt);

                        //and set it to this day
                        lesson.setDay(day);

                        //need to set time
                        int lessonNumber = random.nextInt(1, 5);
                        lesson.setLessonNumber(LessonNumber.fromValue(lessonNumber));
                        Position currentPosition = new Position(day, LessonNumber.fromValue(lessonNumber));
                        log.trace("Propose add new lesson : " + lesson);
                        //need to get lessons already existing for this day
                        Map<Position, Lesson> daySchedule = schedule.forDayByGroup(day, group);
                        log.trace("current schedule for this day : " + daySchedule);
                        if (daySchedule.get(currentPosition) != null) {
                            // lesson already exists on this position
                            log.trace("another lesson already exists on this position. Shifting the position...");
                            shiftPositionWhileHasGap(currentPosition, daySchedule, lesson);
                        }
                        if (checkGap(daySchedule, currentPosition)) {
                            shiftPositionWhileHasGap(currentPosition, daySchedule, lesson);
                        }
                        //try to add new lesson with setted time to schedule
                        log.trace("Try to add this lesson to global schedule");
                        if (schedule.addLesson(lesson)) {
                            log.trace("Lesson successfully added to global schedule!");
                            lessonList.remove(attempt);
                            break;
                        } else {
                            log.trace("Lesson not added. Retry.");
                            lesson.setDay(null);
                            lesson.setLessonNumber(null);
                            attempt++;
                        }
                    }
                }
            }
        });
        log.info("Schedule successfully selected");
        return schedule;
    }

    @Transactional
    @Override
    public void fillDbWithGeneratedData(UniversityDataDto universityDataDto, Schedule globalSchedule) {
        adminRepository.saveAll(universityDataDto.getAdmins());
        log.debug("Saved {} Admins to database.", universityDataDto.getAdmins().size());

        courseRepository.saveAll(universityDataDto.getCourses());
        log.debug("saved {} Courses to database", universityDataDto.getCourses().size());

        classroomRepository.saveAll(universityDataDto.getClassrooms());
        log.debug("Saved {} Classrooms to database", universityDataDto.getClassrooms().size());

        teacherRepository.saveAll(universityDataDto.getTeachers());
        log.debug("Saved {} Teachers to database", universityDataDto.getTeachers().size());

        groupRepository.saveAll(universityDataDto.getGroups());
        log.debug("Saved {} Groups to database", universityDataDto.getGroups());

        studentRepository.saveAll(universityDataDto.getStudents());
        log.debug("Saved {} Students to database", universityDataDto.getStudents());

        lessonRepository.saveAll(globalSchedule.getAllLessons());
        log.debug("Saved {} Lessons to database", globalSchedule.getAllLessons().size());

        log.info("University data and schedule are saved to database.");
    }

    private void shiftPositionWhileHasGap(Position currentPosition, Map<Position, Lesson> daySchedule, Lesson lesson) {
        do {
            shift(currentPosition);
        } while (checkGap(daySchedule, currentPosition));
        log.trace("Position shifted. New position is [{}]. Updating position for current lesson...", currentPosition);
        lesson.setDay(currentPosition.getDay());
        lesson.setLessonNumber(currentPosition.getLessonNumber());
    }

    private boolean checkGap(Map<Position, Lesson> daySchedule, Position position) {
        int[] positions = new int[NUMBER_OF_LESSONS_PER_DAY];
        positions[position.getLessonNumber().getValue() - 1] = position.getLessonNumber().getValue();
        daySchedule.forEach((pos, lesson) -> {
            if (lesson != null) {
                positions[pos.getLessonNumber().getValue() - 1] = pos.getLessonNumber().getValue();
            }
        });
        log.trace("Checking gap in day schedule. " + Arrays.toString(positions));
        long lessonsThisDay = Arrays.stream(positions)
                .filter(value -> value != 0)
                .count();
        int count = 0;
        for (int i = 0; i < NUMBER_OF_LESSONS_PER_DAY - 1; i++) {
            if (positions[i] + 1 == positions[i + 1]) {
                count++;
            }
        }
        log.trace("count = " + count);
        return count < lessonsThisDay - 1;
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
}
