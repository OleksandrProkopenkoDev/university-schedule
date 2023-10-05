package ua.foxminded.task31.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "students")
public class Student extends UserEntity{

    @ManyToOne(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinColumn(name = "group_id", referencedColumnName = "group_id")
    private Group group;


    public Student(String firstName, String lastName) {
        super.setFirstName(firstName);
        super.setLastName(lastName);
    }

    public Student(Group group, String firstName, String lastName) {
        this.group = group;
        super.setFirstName(firstName);
        super.setLastName(lastName);
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
