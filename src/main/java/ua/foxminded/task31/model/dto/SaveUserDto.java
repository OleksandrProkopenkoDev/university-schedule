package ua.foxminded.task31.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.foxminded.task31.model.entity.Course;
import ua.foxminded.task31.model.entity.Group;
import ua.foxminded.task31.model.enums.Role;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SaveUserDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
    private Group group;
    private Course course;
}
