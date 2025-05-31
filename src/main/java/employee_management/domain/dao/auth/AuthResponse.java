package employee_management.domain.dao.auth;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private boolean success;
    private String message;
    private Object data;

    /**
     * Clean, consistent, and foolproof:
     * AuthResponse.success("User retrieved successfully", userInfo);
     * AuthResponse.success("Logout successful");  as No data needed
     * AuthResponse.error("Invalid username or password");
     */

    public static AuthResponse success(String message, Object data) {
        return AuthResponse.builder()
                .success(true)
                .message(message)
                .data(data)
                .build();
    }

    public static AuthResponse success(String message) {
        return AuthResponse.builder()
                .success(true)
                .message(message)
                .build();
    }

    public static AuthResponse error(String message) {
        return AuthResponse.builder()
                .success(false)
                .message(message)
                .build();
    }
}