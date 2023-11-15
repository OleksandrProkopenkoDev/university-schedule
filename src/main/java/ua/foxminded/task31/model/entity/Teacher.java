package ua.foxminded.task31.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
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

    public Teacher(String firstName, String lastName) {
        super(firstName, lastName);
        this.setRole(Role.TEACHER);
    }

    public Teacher(String firstName, String lastName, String username, String password, Role role, Course teachCourse) {
        super(firstName, lastName, username, password, role);
        this.teachCourse = teachCourse;
    }
}
