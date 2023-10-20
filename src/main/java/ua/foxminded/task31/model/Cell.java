package ua.foxminded.task31.model;

import ua.foxminded.task31.model.entity.Group;
import ua.foxminded.task31.model.entity.Lesson;
import ua.foxminded.task31.exception.ScheduleException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cell {
    private final List<Lesson> lessons = new ArrayList<>();

    public boolean addLesson(Lesson lesson) {
        boolean match = lessons.stream()
                .anyMatch(existing -> existing.equals(lesson));
        if (match) {
            return false;
        }
        lessons.add(lesson);
        return true;
    }

    public boolean removeLesson(Lesson lesson) {
        return lessons.remove(lesson);
    }

    public List<Lesson> getLessons() {
        return lessons;
    }


    public Lesson getLessonForGroup(Group group) {
        List<Lesson> lessonList = lessons.stream()
                .filter(lesson -> lesson.getGroup().equals(group))
                .collect(Collectors.toList());
        if (lessonList.size() == 1) {
            return lessonList.get(0);
        } else if (lessonList.isEmpty()) {
            return null;
        } else {
            throw new ScheduleException("There are more than one lesson at time for " + group);
        }
    }

    @Override
    public String toString() {
        return "Cell{" +
                "lessons=" + lessons +
                '}';
    }
}
