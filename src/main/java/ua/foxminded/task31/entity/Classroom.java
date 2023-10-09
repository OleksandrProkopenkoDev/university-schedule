package ua.foxminded.task31.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "classrooms")
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "classroom_id")
    private Long id;
    private String number;
    private String description;

    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST
            }
    )
    @JoinTable(
            name = "classrooms_courses",
            joinColumns = @JoinColumn(name = "classroom_id", referencedColumnName = "classroom_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "course_id")
    )
    private List<Course> availableCourses;

    public Classroom(String number, String description) {
        this.number = number;
        this.description = description;
    }
}
