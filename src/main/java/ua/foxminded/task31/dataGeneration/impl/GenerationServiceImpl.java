package ua.foxminded.task31.dataGeneration.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.foxminded.task31.model.dto.UniversityDataDto;
import ua.foxminded.task31.exception.GenerationException;
import ua.foxminded.task31.dataGeneration.GenerationService;
import ua.foxminded.task31.model.entity.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class GenerationServiceImpl implements GenerationService {

    private static final int MIN_LESSONS_PER_WEEK = 12;
    private static final int MAX_LESSONS_PER_WEEK = 18;
    private static final int MIN_TOTAL_COURSES_FOR_GROUP = 5;
    private static final int MAX_TOTAL_COURSES_FOR_GROUP = 9;
    private static final int MAX_LESSONS_PER_WEEK_FOR_COURSE = 4;
    public static final int CHARACTERS_IN_PASSWORD = 8;
    private int userNumber = 0;
    private final Random random = new Random();

    @Override
    public List<Student> generateStudents(int amount) {
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            Student student = new Student(
                    null,
                    firstNames().get(random.nextInt(firstNames().size())),
                    lastNames().get(random.nextInt(lastNames().size()))
            );
            student.setUsername(generateUsername(student));
            student.setPassword(generatePassword());
            students.add(student);
        }
        return students;
    }

    @Override
    public List<Student> generateStudents(int amount, List<Group> groups) {
        List<Student> students = generateStudents(amount);
        int first = 0;
        int last;
        for (Group group : groups) {
            int amountStudentsInGroup = random.nextInt(20) + 10;
            last = first + amountStudentsInGroup;
            if (last > students.size()) {
                last = students.size();
            }
            for (int i = first; i < last; i++) {
                students.get(i).setGroup(group);
            }
            first = last;
        }
        return students;
    }

    @Override
    public List<Course> generateCourses(int amount) {
        List<Course> source = coursesList();
        if (amount > source.size()) {
            amount = source.size();
        }
        return random.ints(0, source.size())
                .distinct()
                .limit(amount)
                .mapToObj(source::get)
                .collect(Collectors.toList());
    }

    @Override
    public List<Group> generateGroups(int amount) {
        List<Group> groups = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            groups.add(generateGroup());
        }
        return groups;
    }

    @Override
    public List<Group> generateGroups(int amount, List<Course> courses) {
        List<Group> groups = generateGroups(amount);
        int[][] curriculumTable = generateCurriculumTable(amount, courses.size());
        for (int i = 0; i < groups.size(); i++) {
            for (int j = 0; j < courses.size(); j++) {
                if (curriculumTable[i][j] != 0) {
                    groups.get(i).getCourses().add(courses.get(j));
                }
            }
        }
        return groups;
    }

    @Override
    public List<Group> generateGroups(int amount, List<Course> courses, int[][] curriculumTable) {
        List<Group> groups = generateGroups(amount);
        for (int i = 0; i < groups.size(); i++) {
            for (int j = 0; j < courses.size(); j++) {
                if (curriculumTable[i][j] != 0) {
                    groups.get(i).getCourses().add(courses.get(j));
                }
            }
        }
        return groups;
    }

    @Override
    public List<Teacher> generateTeachers(int amount) {
        List<Teacher> teachers = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            Teacher teacher = new Teacher(
                    firstNames().get(random.nextInt(firstNames().size())),
                    lastNames().get(random.nextInt(lastNames().size()))
            );
            teacher.setUsername(generateUsername(teacher));
            teacher.setPassword(generatePassword());
            teachers.add(teacher);
        }
        return teachers;
    }

    @Override
    public List<Teacher> generateTeachers(int amount, List<Course> courses) {
        List<Teacher> teachers = generateTeachers(amount);
        for (int i = 0; i < teachers.size(); i++) {
            teachers.get(i).setTeachCourse(courses.get(i % courses.size()));
        }

        return teachers;
    }

    @Override
    public List<Admin> generateAdmins(int amount) {
        List<Admin> admins = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            Admin admin = new Admin(
                    firstNames().get(random.nextInt(firstNames().size())),
                    lastNames().get(random.nextInt(lastNames().size()))
            );
            admin.setUsername(generateUsername(admin));
            admin.setPassword(generatePassword());
            admins.add(admin);
        }
        return admins;
    }

    @Override
    public List<Classroom> generateClassrooms(int amount) {
        List<Classroom> classrooms = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            classrooms.add(generateClassroom());
        }
        return classrooms;
    }

    @Override
    public List<Classroom> generateClassrooms(int amount, List<Course> courses) {
        List<Classroom> classrooms = generateClassrooms(amount);

        classrooms.forEach(classroom -> setAvailableCoursesFromList(courses, classroom));

        return classrooms;
    }

    private void setAvailableCoursesFromList(List<Course> courses, Classroom classroom) {
        String description = classroom.getDescription();
        List<Course> collected = courses.stream()
                .filter(course -> description.startsWith(course.getName().split(" ")[0]))
                .collect(Collectors.toList());
        int number = random.nextInt(1, 4);
        for (int i = 0; i < number; i++) {
            collected.add(courses.get(random.nextInt(courses.size())));
        }
        List<Course> courseList = collected.stream()
                .distinct()
                .collect(Collectors.toList());
        classroom.setAvailableCourses(courseList);
    }

    @Override
    public int[][] generateCurriculumTable(int groupsNumber, int coursesNumber) {
        int[][] curriculum = new int[groupsNumber][coursesNumber];

        for (int i = 0; i < curriculum.length; i++) {

            int totalLessonsAmount = 0;
            while (totalLessonsAmount < MIN_LESSONS_PER_WEEK || totalLessonsAmount > MAX_LESSONS_PER_WEEK) {
                Arrays.fill(curriculum[i], 0);
                totalLessonsAmount = 0;
                int totalCoursesCount = random.nextInt(MIN_TOTAL_COURSES_FOR_GROUP, MAX_TOTAL_COURSES_FOR_GROUP);
                int startJShift = random.nextInt(coursesNumber - totalCoursesCount);
                int courseCount = 0;
                for (int j = startJShift; j < curriculum[i].length; j++) {
                    curriculum[i][j] = random.nextInt(MAX_LESSONS_PER_WEEK_FOR_COURSE);
                    totalLessonsAmount += curriculum[i][j];
                    if (curriculum[i][j] != 0) {
                        courseCount++;
                    }
                    if (courseCount == totalCoursesCount) {
                        break;
                    }
                }
            }
        }
        return curriculum;
    }

    @Override
    public List<Lesson> generateLessonsFrom(UniversityDataDto universityDataDto) {
        List<Lesson> lessons = new ArrayList<>();
        int[][] curriculumTable = universityDataDto.getCurriculumTable();

        for (int i = 0; i < curriculumTable.length; i++) {
            for (int j = 0; j < curriculumTable[i].length; j++) {
                if (curriculumTable[i][j] != 0) {
                    for (int k = 0; k < curriculumTable[i][j]; k++) {
                        Course course = universityDataDto.getCourses().get(j);
                        Group group = universityDataDto.getGroups().get(i);

                        Classroom classroom = selectClassroomForCourse(universityDataDto.getClassrooms(), course);
                        Teacher teacher = selectTeacherForCourse(universityDataDto.getTeachers(), course);

                        lessons.add(new Lesson(
                                classroom,
                                group,
                                teacher,
                                course
                        ));
                    }
                }
            }
        }
        return lessons;
    }

    private Teacher selectTeacherForCourse(List<Teacher> teachers, Course course) {
        List<Teacher> teachersList = teachers.stream()
                .filter(teacher -> teacher.getTeachCourse().getName().equals(course.getName()))
                .collect(Collectors.toList());
        if (teachersList.isEmpty()) {
            throw new GenerationException("There is no available teachers for " + course);
        }
        int num = random.nextInt(teachersList.size());

        return teachersList.get(num);
    }

    private Classroom selectClassroomForCourse(List<Classroom> classrooms, Course course) {
        List<Classroom> classroomList = classrooms.stream()
                .filter(classroom -> classroom.getAvailableCourses().stream()
                        .anyMatch(course1 -> course1.getName().equals(course.getName())))
                .collect(Collectors.toList());
        if (classroomList.isEmpty()) {
            throw new GenerationException("There is no available classrooms for " + course);
        }
        int num = random.nextInt(classroomList.size());

        return classroomList.get(num);
    }


    private Classroom generateClassroom() {
        String building = "ABCD";
        String number = String.valueOf(
                building.charAt(random.nextInt(building.length()))) +
                random.nextInt(1, 4) +
                "-" +
                random.nextInt(1, 4) +
                random.nextInt(3) +
                random.nextInt(9);
        return new Classroom(
                number,
                classroomDescriptions().get(random.nextInt(classroomDescriptions().size()))
        );
    }

    private String generateUsername(UserEntity user) {
        if (user.getFirstName() == null || user.getLastName() == null) {
            throw new GenerationException(user + " has no first or last name");
        }
        userNumber++;
        return user.getFirstName() + "." + user.getLastName() + userNumber + "@gmail.com";
    }

    private String generatePassword() {
        String source = "1234567890QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm_!@#$%^&*";
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < CHARACTERS_IN_PASSWORD; i++) {
            password.append(source.charAt(random.nextInt(source.length())));
        }
        return password.toString();
    }

    private Group generateGroup() {
        Group group = new Group();
        String source = "QWERTYUIOPASDFGHJKLZXCVBNM";
        String builder = String.valueOf(source.charAt(random.nextInt(source.length()))) +
                source.charAt(random.nextInt(source.length())) +
                "-" +
                random.nextInt(9) +
                random.nextInt(9);
        group.setName(builder);
        return group;
    }

    private List<String> classroomDescriptions() {
        return List.of(
                "Psychology Classroom: Designed for interactive discussions and psychological experiments. Equipment: Whiteboard, projector, comfortable seating. Capacity: 30 seats. Type: Interactive discussion and experimental classroom.",
                "World History Classroom: Ideal for historical lectures and group discussions. Equipment: Maps, projector, podium. Capacity: 40 seats. Type: Lecture and discussion room.",
                "Economics Classroom: Facilitates economic simulations and theoretical discussions. Equipment: Smartboard, computers, seating with tables. Capacity: 25 seats. Type: Simulation and discussion room.",
                "Literature Classroom: Fosters literary analysis and group readings. Equipment: Library corner, projector, comfortable seating. Capacity: 35 seats. Type: Reading and discussion room.",
                "Environmental Science Lab: Equipped for hands-on experiments and data analysis. Equipment: Microscopes, specimens, lab stations. Capacity: 20 seats. Type: Experimental laboratory.",
                "Computer Science Lab: Practical programming sessions and software development. Equipment: Computers, coding boards, comfortable seating. Capacity: 25 seats. Type: Computer lab.",
                "Statistics Classroom: Analytical discussions and statistical modeling. Equipment: Whiteboard, statistical software, tables. Capacity: 30 seats. Type: Discussion and analysis room.",
                "Political Science Classroom: Dynamic discussions on global political issues. Equipment: Projector, podium, round-table seating. Capacity: 40 seats. Type: Round-table discussion room.",
                "Cultural Anthropology Classroom: Exploration of cultures and societies. Equipment: Cultural artifacts, whiteboard, flexible seating. Capacity: 35 seats. Type: Flexible discussion room.",
                "Business Management Classroom: Strategy discussions and case studies. Equipment: Presentation board, seminar tables, comfortable seating. Capacity: 30 seats. Type: Seminar room.",
                "Physics Lecture Hall: Large-scale physics lectures and demonstrations. Equipment: Large projector, interactive boards, tiered seating. Capacity: 150 seats. Type: Lecture hall.",
                "Philosophy Classroom: Reflective discussions and philosophical debates. Equipment: Whiteboard, comfortable seating. Capacity: 25 seats. Type: Discussion room.",
                "Creative Writing Workshop: Inspiring creativity through writing exercises. Equipment: Writing boards, inspiring decor, comfortable seating. Capacity: 20 seats. Type: Workshop room.",
                "Spanish Language Lab: Language immersion and communication practice. Equipment: Language software, audio stations, comfortable seating. Capacity: 25 seats. Type: Language lab.",
                "Sociology Classroom: Societal analysis and group discussions. Equipment: Whiteboard, projector, round-table seating. Capacity: 30 seats. Type: Round-table discussion room.",
                "Ethics Classroom: Ethical debates and case studies. Equipment: Discussion boards, comfortable seating. Capacity: 25 seats. Type: Discussion room.",
                "Film Studies Classroom: Cinematic analysis and film screenings. Equipment: Projector, sound system, tiered seating. Capacity: 40 seats. Type: Screening room.",
                "Human Biology Lab: Hands-on anatomy experiments and dissections. Equipment: Lab stations, specimens, microscopes. Capacity: 20 seats. Type: Biology lab.",
                "Digital Marketing Classroom: Interactive discussions on digital strategies. Equipment: Smartboards, computers, comfortable seating. Capacity: 30 seats. Type: Discussion and workshop room.",
                "Archaeology Lab: Artifact analysis and archaeological simulations. Equipment: Lab stations, artifacts, comfortable seating. Capacity: 25 seats. Type: Archaeological laboratory.",
                "Introduction to Astronomy Classroom: Observational astronomy and celestial simulations. Equipment: Telescopes, planetarium software, comfortable seating. Capacity: 25 seats. Type: Observational and simulation room.",
                "Chemistry Lab: Chemical experiments and laboratory research. Equipment: Lab stations, chemicals, safety apparatus. Capacity: 20 seats. Type: Chemistry laboratory.",
                "Mathematics Workshop: Problem-solving sessions and mathematical discussions. Equipment: Whiteboards, mathematical models, seminar tables. Capacity: 30 seats. Type: Workshop room.",
                "Geography Classroom: Spatial analysis and geographic discussions. Equipment: Maps, globe, whiteboard, comfortable seating. Capacity: 35 seats. Type: Discussion and analysis room.",
                "Art History Lecture Hall: Artistic analysis and visual presentations. Equipment: Projector, art displays, tiered seating. Capacity: 100 seats. Type: Lecture hall.",
                "Engineering Design Studio: Collaborative engineering projects and design challenges. Equipment: Design tools, prototyping stations, collaborative tables. Capacity: 30 seats. Type: Design studio.",
                "Health and Wellness Seminar Room: Discussions on physical and mental well-being. Equipment: Yoga mats, interactive boards, comfortable seating. Capacity: 25 seats. Type: Seminar room.",
                "Political Economy Classroom: Analyzing the intersection of politics and economics. Equipment: Whiteboard, projector, round-table seating. Capacity: 35 seats. Type: Round-table discussion room.",
                "Music Appreciation Room: Musical analysis and appreciation sessions. Equipment: Sound system, musical instruments, comfortable seating. Capacity: 30 seats. Type: Music appreciation room.",
                "Robotics Lab: Hands-on robotics projects and programming. Equipment: Robotic kits, programming stations, collaborative tables. Capacity: 20 seats. Type: Robotics laboratory."
        );
    }

    private List<String> lastNames() {
        return List.of(
                "Smith", "Johnson", "Williams", "Jones", "Brown", "Davis",
                "Miller", "Wilson", "Moore", "Taylor", "Anderson", "Thomas",
                "Jackson", "White", "Harris", "Martin", "Thompson", "Garcia",
                "Martinez", "Robinson"
        );
    }

    private List<String> firstNames() {
        return List.of(
                "Emily", "Michael", "Sophia", "William", "Olivia", "James",
                "Ava", "John", "Isabella", "David", "Mia", "Robert",
                "Emma", "Joseph", "Charlotte", "Daniel", "Amelia",
                "Matthew", "Liam", "Grace"
        );
    }

    private List<Course> coursesList() {
        return List.of(
                new Course("Introduction to Java Programming", "Learn the basics of Java programming, including syntax, data types, and control structures."),
                new Course("Object-Oriented Programming with Java", "Explore object-oriented concepts like classes, objects, inheritance, and polymorphism in Java."),
                new Course("Java GUI Development", "Focus on creating graphical user interfaces (GUIs) using Java's Swing or JavaFX libraries."),
                new Course("Data Structures and Algorithms in Java", "Study fundamental data structures and algorithms, and implement them using Java."),
                new Course("Web Application Development with Java Servlets and JSP", "Learn to build dynamic web applications using Java Servlets and JavaServer Pages (JSP)."),
                new Course("Enterprise JavaBeans (EJB) Development", "Dive into building distributed, component-based enterprise applications using EJB."),
                new Course("Java Spring Framework Fundamentals", "An introduction to the Spring Framework for building Java-based enterprise applications."),
                new Course("Mobile App Development with Java and Android", "Develop mobile applications for Android using Java and the Android SDK."),
                new Course("Advanced Java Topics: Concurrency and Multithreading", "Explore advanced Java features, including concurrency, threading, and parallel programming."),
                new Course("Java Persistence with Hibernate", "Learn how to integrate Hibernate for object-relational mapping (ORM) in Java applications."),
                new Course("Introduction to Biology", "Explore the fundamental concepts of biology, including cellular structure, genetics, and evolution."),
                new Course("Chemistry: Principles and Applications", "Learn the basics of chemistry, including atomic structure, chemical reactions, and molecular properties."),
                new Course("Physics for Engineers", "Study the fundamental principles of physics, including mechanics, electromagnetism, and thermodynamics."),
                new Course("Introduction to Psychology", "Discover the basics of psychology, covering topics such as behavior, cognition, and psychological disorders."),
                new Course("Environmental Science and Sustainability", "Examine environmental issues and learn about sustainable practices to protect our planet."),
                new Course("Introduction to Political Science", "Analyze political systems, government structures, and international relations."),
                new Course("Economics: Microeconomics and Macroeconomics", "Understand the principles of microeconomics (individual behavior) and macroeconomics (economy-wide factors)."),
                new Course("History of Art and Architecture", "Survey the history of artistic movements and architectural styles throughout different periods."),
                new Course("Astronomy: Exploring the Universe", "Study celestial objects, galaxies, and the origins of the universe."),
                new Course("Introduction to Linguistics", "Explore the scientific study of language, including phonetics, syntax, and language acquisition."),

                new Course("Introduction to Psychology", "Explore the fundamental principles of psychology and human behavior."),
                new Course("World History: From Ancient Civilizations to Modern Times", "Survey the major events and developments in world history."),
                new Course("Basic Economics: Understanding Market Forces", "An introduction to economic principles and the functioning of markets."),
                new Course("Literature and Society", "Examine the relationship between literature and societal values throughout history."),
                new Course("Environmental Science: A Global Perspective", "Study the interconnections between ecosystems and human activities."),
                new Course("Introduction to Computer Science", "Learn the basics of programming, algorithms, and computer systems."),
                new Course("Statistics for Decision Making", "Develop statistical literacy and analytical skills for informed decision-making."),
                new Course("Contemporary Political Issues", "Explore current political challenges and debates on a global scale."),
                new Course("Cultural Anthropology: Understanding Human Societies", "Investigate the diversity of human cultures and societies."),
                new Course("Fundamentals of Business Management", "An overview of key principles and practices in business management."),
                new Course("Physics for Non-Science Majors", "An introductory course covering basic principles of physics in everyday life."),
                new Course("Introduction to Philosophy", "Examine fundamental philosophical questions and major philosophical traditions."),
                new Course("Creative Writing Workshop", "Develop writing skills and explore various genres in a creative workshop setting."),
                new Course("Spanish Language and Culture", "Learn the fundamentals of the Spanish language and explore Hispanic cultures."),
                new Course("Introduction to Sociology", "Study the structure and dynamics of human societies and social relationships."),
                new Course("Introduction to Ethics", "Discuss ethical theories and apply them to real-world moral dilemmas."),
                new Course("Introduction to Film Studies", "Analyze and appreciate the art and cultural impact of films from various genres."),
                new Course("Human Biology: Anatomy and Physiology", "Explore the structure and function of the human body."),
                new Course("Digital Marketing Fundamentals", "Learn the basics of digital marketing strategies and tools."),
                new Course("Introduction to Archaeology", "Explore the methods and discoveries of archaeological research.")
        );
    }
}
