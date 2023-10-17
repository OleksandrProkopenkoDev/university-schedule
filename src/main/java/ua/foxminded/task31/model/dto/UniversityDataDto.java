package ua.foxminded.task31.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.foxminded.task31.entity.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UniversityDataDto {
    private List<Admin> admins;
    private List<Course> courses;
    private List<Classroom> classrooms;
    private List<Teacher> teachers;
    private int[][] curriculumTable;
    private List<Group> groups;
    private List<Student> students;
}
