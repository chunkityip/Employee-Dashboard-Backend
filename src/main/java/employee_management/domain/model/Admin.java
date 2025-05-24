package employee_management.domain.model;

import employee_management.domain.enums.Entitlement;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
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

    private byte[] profileImageData;

    // (Optional) metadata
    private String profileImageFilename;
    private String profileImageContentType;

    private Set<Entitlement> entitlements = new HashSet<>();
}
