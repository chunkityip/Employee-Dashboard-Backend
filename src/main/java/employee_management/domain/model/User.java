package employee_management.domain.model;


import employee_management.domain.enums.Role;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class User {
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
