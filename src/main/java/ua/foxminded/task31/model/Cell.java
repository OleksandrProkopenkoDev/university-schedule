package ua.foxminded.task31.model;

import ua.foxminded.task31.entity.Lesson;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    private List<Lesson> lessons = new ArrayList<>();

    public boolean addLesson(Lesson lesson){
        boolean match = lessons.stream()
                .anyMatch(existing -> existing.equals(lesson));
        if(match){
            return false;
        }
        lessons.add(lesson);
        return true;
    }

    public boolean removeLesson(Lesson lesson){
        return lessons.remove(lesson);
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "lessons=" + lessons +
                '}';
    }
}
