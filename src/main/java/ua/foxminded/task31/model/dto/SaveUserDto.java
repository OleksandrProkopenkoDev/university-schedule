package ua.foxminded.task31.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
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
    @NotEmpty(message = "First name can't be empty")
    private String firstName;
    @NotEmpty(message = "Last name can't be empty")
    private String lastName;
    @Email(message = "Invalid email address")
    @NotEmpty(message = "Email can't be empty")
    private String email;
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;
    private Role role;
    private Group group;
    private Course course;
}
