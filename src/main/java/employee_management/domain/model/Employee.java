package employee_management.domain.model;

import employee_management.domain.enums.entitlement.AdminEntitlement;
import employee_management.domain.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;

    private boolean active;
    private Long version;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private byte[] profileImage;
    private String profileImageFilename;
    private String profileImageContentType;

    private Role role;
}
