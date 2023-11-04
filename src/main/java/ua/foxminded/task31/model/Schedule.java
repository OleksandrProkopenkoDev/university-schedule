package ua.foxminded.task31.model;

import lombok.Getter;
import ua.foxminded.task31.model.entity.Group;
import ua.foxminded.task31.model.entity.Lesson;
import ua.foxminded.task31.model.enums.Day;
import ua.foxminded.task31.model.enums.LessonNumber;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class Schedule {

    private final Map<Position, Cell> schedule = new HashMap<>();

    public Schedule() {
        for (Day day: Day.values()) {
            for (LessonNumber lessonNumber: LessonNumber.values()) {
                Position position = new Position(day, lessonNumber);
                Cell cell = new Cell();
                schedule.put(position, cell);
            }
        }
    }

    public Schedule(List<Lesson> lessons) {
        for (Day day: Day.values()) {
            for (LessonNumber lessonNumber: LessonNumber.values()) {
                Position position = new Position(day, lessonNumber);
                Cell cell = new Cell();
                List<Lesson> lessonList = lessons.stream()
                        .filter(lesson -> lesson.getDay().equals(day) && lesson.getLessonNumber().equals(lessonNumber))
                        .collect(Collectors.toList());
                cell.addLessons(lessonList);
                schedule.put(position, cell);
            }
        }
    }


    public boolean addLesson(Lesson lesson) {
        Position position = new Position(lesson.getDay(), lesson.getLessonNumber());
        Cell cell = schedule.get(position);
        return cell.addLesson(lesson);
    }

    public void print(){
        for (Day day: Day.values()) {
            System.out.println(day + " ");
            for (LessonNumber lessonNumber: LessonNumber.values()) {
                System.out.print(lessonNumber + " ");
                System.out.println(schedule.get(new Position(day, lessonNumber)));
            }
        }
    }

    public void printForGroup(Map<Position, Lesson> lessonMap){
        for (Day day: Day.values()) {
            System.out.println(day + " ");
            for (LessonNumber lessonNumber: LessonNumber.values()) {
                System.out.print(lessonNumber + " ");
                System.out.println(lessonMap.get(new Position(day, lessonNumber)));
            }
        }
    }

    public Map<Position, Lesson> forGroup(Group group) {
        Map<Position, Lesson> lessonMap = new HashMap<>();
        schedule.forEach((position, cell) -> {
            Lesson lesson = cell.getLessonForGroup(group);
            lessonMap.put(position, lesson);
        });
        return lessonMap;
    }

    public Map<Position, Lesson> forDayByGroup(Day day, Group group) {
        Map<Position, Lesson> lessonMap = new HashMap<>();
        schedule.forEach((position, cell) -> {
            if(position.getDay().equals(day)){
                lessonMap.put(position, cell.getLessonForGroup(group) );
            }
        });
        return lessonMap;
    }

    public List<Lesson> getAllLessons() {
        List<Lesson> lessons = new ArrayList<>();
        for (Day day: Day.values()) {
            for (LessonNumber lessonNumber: LessonNumber.values()) {
                Position position = new Position(day, lessonNumber);
                Cell cell = schedule.get(position);
                lessons.addAll(cell.getLessons());
            }
        }
        return lessons;
    }

    public Cell getCell(Day day, LessonNumber lessonNumber){
        return schedule.get(new Position(day, lessonNumber));
    }
}
