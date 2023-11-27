package ua.foxminded.task31.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ua.foxminded.task31.model.enums.Role;

@Getter
@Setter
@Entity
@Table(name = "admins")
public class Admin extends UserEntity {

    public Admin() {
        this.setRole(Role.ADMIN);
    }

    public Admin(String firstName, String lastName) {
        super(firstName, lastName);
        this.setRole(Role.ADMIN);
    }

    public Admin(String firstName, String lastName, String username, String password, Role role) {
        super(firstName, lastName, username, password, role);
    }

    public Admin(Long id, String firstName, String lastName, String username, String password, Role role) {
        super(id, firstName, lastName, username, password, role);
    }
}
