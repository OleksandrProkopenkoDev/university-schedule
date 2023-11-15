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
@Table(name = "students")
public class Student extends UserEntity {

    @ManyToOne(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinColumn(name = "group_id", referencedColumnName = "group_id")
    private Group group;

    public Student() {
        this.setRole(Role.STUDENT);
    }

    public Student(String firstName, String lastName) {
        super.setFirstName(firstName);
        super.setLastName(lastName);
        this.setRole(Role.STUDENT);
    }

    public Student(Group group, String firstName, String lastName) {
        super.setFirstName(firstName);
        super.setLastName(lastName);
        this.group = group;
        this.setRole(Role.STUDENT);
    }

    public Student(String firstName, String lastName, String username, String password, Role role, Group group) {
        super(firstName, lastName, username, password, role);
        this.group = group;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + super.getId() +
                ", " + group +
                ", firstName='" + super.getFirstName() + '\'' +
                ", lastName='" + super.getLastName() + '\'' +
                '}';
    }
}
