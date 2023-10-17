package ua.foxminded.task31.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ua.foxminded.task31.model.enums.Day;
import ua.foxminded.task31.model.enums.LessonNumber;

import java.util.Objects;

@AllArgsConstructor
@Getter
@Setter
public class Position {
    private Day day;
    private LessonNumber lessonNumber;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return day == position.day && lessonNumber == position.lessonNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(day, lessonNumber);
    }

    @Override
    public String toString() {
        return "Position{" + day +
                " - " + lessonNumber +
                '}';
    }
}
