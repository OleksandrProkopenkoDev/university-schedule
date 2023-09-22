package ua.foxminded.task31.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Long id;
    @Column(name = "course_name")
    private String name;
    @Column(name = "course_description")
    private String description;

    public Course(String name, String description) {
        this.name = name;
        this.description = description;
    }

}
