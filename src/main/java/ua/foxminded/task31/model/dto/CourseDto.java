package ua.foxminded.task31.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseDto {
    private Long id;
    @NotEmpty(message = "Course name can't be empty")
    @Size(min = 3, message = "Course name must be at least 3 characters")
    private String name;
    @NotEmpty(message = "Course description can't be empty")
    @Size(min = 10, message = "Course name must be at least 10 characters")
    private String description;
}
