package employee_management.service;

import employee_management.domain.dao.UserDao;
import employee_management.domain.dao.auth.AuthResponse;
import employee_management.domain.dao.auth.LoginRequest;
import employee_management.domain.dao.auth.LoginResponse;
import employee_management.domain.enums.Role;
import employee_management.domain.model.User;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    private final UserDao userDao;

    // Simple in-memory session storage (for demo purposes)
    // In production, use Redis or database
    private final Map<String, SessionInfo> activeSessions = new ConcurrentHashMap<>();

    @Override
    public LoginResponse login(LoginRequest loginRequest) throws SQLException {
        logger.info("AuthService: Processing login request for username: {}", loginRequest.getUsername());


        try {
            Optional<User> userOpt = userDao.findByUsernameAndPassword(
                    loginRequest.getUsername().trim(),
                    loginRequest.getPassword()
            );

            if (userOpt.isPresent()) {
                User user = userOpt.get();

                // Check if user is active (additional safety check)
                if (!user.isActive()) {
                    logger.warn("AuthService: Login failed - user inactive: {}", loginRequest.getUsername());
                    return LoginResponse.builder()
                            .success(false)
                            .message("Account is inactive. Please contact administrator.")
                            .build();
                }

                // Generate session token
                String sessionToken = generateSessionToken();

                // Store session in memory
                SessionInfo sessionInfo = SessionInfo.builder()
                        .userId(user.getId())
                        .username(user.getUsername())
                        .role(user.getRole())
                        .loginTime(LocalDateTime.now())
                        .lastAccessTime(LocalDateTime.now())
                        .build();

                activeSessions.put(sessionToken, sessionInfo);

                // Update last login time in database
                userDao.updateLastLogin(user.getId());

                // Build user info response (without password)
                LoginResponse.UserInfo userInfo = LoginResponse.UserInfo.builder()
                        .id(user.getId())
                        .username(user.getUsername())
                        .email(user.getEmail())
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .role(user.getRole())
                        .active(user.isActive())
                        .lastLogin(LocalDateTime.now())
                        .build();

                logger.info("AuthService: Login successful for username: {}, session: {}",
                        loginRequest.getUsername(), sessionToken);

                return LoginResponse.builder()
                        .success(true)
                        .message("Login successful")
                        .userInfo(userInfo)
                        .sessionToken(sessionToken)
                        .build();

            } else {
                logger.warn("AuthService: Login failed - invalid credentials for username: {}", loginRequest.getUsername());
                return LoginResponse.builder()
                        .success(false)
                        .message("Invalid username or password")
                        .build();
            }

        } catch (SQLException e) {
            logger.error("AuthService: Database error during login for username: {}", loginRequest.getUsername(), e);
            return LoginResponse.builder()
                    .success(false)
                    .message("Login service temporarily unavailable. Please try again.")
                    .build();
        } catch (Exception e) {
            logger.error("AuthService: Unexpected error during login for username: {}", loginRequest.getUsername(), e);
            return LoginResponse.builder()
                    .success(false)
                    .message("An unexpected error occurred. Please try again.")
                    .build();
        }
    }

    @Override
    public boolean logout(String sessionToken) throws SQLException {
        return false;
    }

    @Override
    public boolean isValidSession(String sessionToken) throws SQLException {
        return false;
    }

    @Override
    public AuthResponse getCurrentUser(String sessionToken) throws SQLException {
        return null;
    }

    @Override
    public AuthResponse validateSession(String sessionToken) throws SQLException {
        return null;
    }

    private String generateSessionToken() {
        return UUID.randomUUID().toString() + "-" + System.currentTimeMillis();
    }

    @Data
    @Builder
    private static class SessionInfo {
        private Long userId;
        private String username;
        private Role role;
        private LocalDateTime loginTime;
        private LocalDateTime lastAccessTime;
    }
}
