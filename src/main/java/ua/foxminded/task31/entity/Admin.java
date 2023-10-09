package ua.foxminded.task31.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
}
