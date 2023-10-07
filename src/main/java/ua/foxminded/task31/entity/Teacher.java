package ua.foxminded.task31.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ua.foxminded.task31.model.enums.Role;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "teachers")
public class Teacher extends UserEntity {

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "teach_course_id", referencedColumnName = "course_id")
    private Course teachCourse;

    public Teacher() {
        this.setRole(Role.TEACHER);
    }
}
