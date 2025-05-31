package employee_management.domain.model;

import employee_management.domain.enums.Role;
import employee_management.domain.enums.entitlement.AdminEntitlement;
import lombok.*;

import java.time.LocalDateTime;
import java.util.EnumSet;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Admin {
    private Long id;

    private String username;

    private String password;

    private String email;

    private String firstName;

    private String lastName;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    // can disable without deleting
    private boolean active;

    // optimistic-lock/version
    private Long version;

    private byte[] profileImage;
    // (Optional) metadata
    private String profileImageFilename;
    private String profileImageContentType;

    private Role role = Role.ADMIN;

    // Admin-specific capabilities
    // Initialize entitlements to avoid NullPointerException
    private EnumSet<AdminEntitlement> entitlements = EnumSet.noneOf(AdminEntitlement.class);
}
