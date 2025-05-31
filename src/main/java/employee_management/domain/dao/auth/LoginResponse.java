package employee_management.domain.dao.auth;

import employee_management.domain.enums.Role;
import employee_management.domain.enums.entitlement.AdminEntitlement;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.EnumSet;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private boolean success;
    private String message;
    private UserInfo userInfo;
    private String sessionToken; // Simple token for session management

    /**
     * Use the nested structure because:
     *
     * ✅ More professional API design
     * ✅ Easier to maintain as your app grows
     * ✅ Industry standard pattern
     * ✅ Better organization
     */

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserInfo {
        private Long id;
        private String username;
        private String email;
        private String firstName;
        private String lastName;
        private Role role;
        private EnumSet<AdminEntitlement> entitlements;
        private boolean active;
        private LocalDateTime lastLogin;
    }
}
