package ua.foxminded.task31.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "curriculum")
public class Curriculum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "curriculum_id")
    private Long id;

    @ManyToOne(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinColumn(name = "group_id", referencedColumnName = "group_id")
    private Group group;

    @ElementCollection
    @CollectionTable(name = "number_of_lessons", joinColumns = @JoinColumn(name = "curriculum_id"))
    @MapKeyColumn(name = "course_id")
    @Column(name = "number_of_lessons")
    private Map<Integer, Integer> numberOfLessons;

    public Curriculum(Group group, Map<Integer, Integer> numberOfLessons) {
        this.group = group;
        this.numberOfLessons = numberOfLessons;
    }
}
