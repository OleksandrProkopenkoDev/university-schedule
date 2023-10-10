package ua.foxminded.task31.model;

import lombok.Getter;
import ua.foxminded.task31.entity.Lesson;
import ua.foxminded.task31.model.enums.Day;
import ua.foxminded.task31.model.enums.LessonNumber;

import java.util.HashMap;
import java.util.Map;

@Getter
public class Schedule {

    private final Map<Position, Cell> schedule = new HashMap<>();

    public Schedule() {
        for (Day day: Day.values()) {
            for (LessonNumber lessonNumber: LessonNumber.values()) {
                Position position = new Position(day, lessonNumber);
                Cell cell = new Cell();
                Cell put = schedule.put(position, cell);
                System.out.println("Put cell: "+ cell);
            }
        }
        System.out.println("in schedule constructor");
        print();
        System.out.println();
        System.out.println(schedule);
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
}
