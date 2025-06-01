package employee_management.domain.dao;

import employee_management.domain.enums.Role;
import employee_management.domain.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDao {

    private static final Logger logger = LoggerFactory.getLogger(UserDao.class);

    @Autowired
    private DataSource dataSource;

    public Optional<User> findByUsernameAndPassword(String username, String password) throws SQLException {
        String sql = "SELECT * FROM user WHERE username = ? AND password = ? AND active = true";

        logger.info("UserDao: Authenticating user: {}", username);

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, username);
            statement.setString(2, password);

            logger.debug("UserDao: Database connection established for authentication");

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    logger.info("UserDao: Authentication successful for username");
                    return Optional.of(buildUserFromResultSet(resultSet));
                } else {
                    logger.warn("UserDao: Authentication failed for username: {}", username);
                    return Optional.empty();
                }
            }
        } catch (SQLException e) {
            logger.error("UserDao: SQLException during authentication for username {}: {}", username, e.getMessage(), e);
            throw new SQLException("Authentication error: " + e.getMessage(), e);
        }
    }


    /**
     * Build User object from ResultSet - FIXED timestamp handling
     */
    private User buildUserFromResultSet(ResultSet rs) throws SQLException {
        return User.builder()
                .id(rs.getLong("id"))
                .username(rs.getString("username"))
                .password(rs.getString("password"))
                .email(rs.getString("email"))
                .firstName(rs.getString("first_name"))
                .lastName(rs.getString("last_name"))
                .active(rs.getBoolean("active"))
                .version(rs.getLong("version"))

                // ✅ OPTION 1: Direct conversion (Recommended)
                .createdAt(rs.getTimestamp("created_at") != null ?
                        rs.getTimestamp("created_at").toLocalDateTime() : null)
                .updatedAt(rs.getTimestamp("updated_at") != null ?
                        rs.getTimestamp("updated_at").toLocalDateTime() : null)

                // ✅ OPTION 2: Use helper method (if you prefer)
                // .createdAt(parseTimestamp(rs.getTimestamp("created_at")))
                // .updatedAt(parseTimestamp(rs.getTimestamp("updated_at")))

                .profileImage(rs.getBytes("profile_image"))
                .profileImageFilename(rs.getString("profile_image_filename"))
                .profileImageContentType(rs.getString("profile_image_content_type"))
                .role(parseRole(rs.getString("role")))
                .build();
    }

    /**
     * Parse role safely
     */
    private Role parseRole(String roleStr) {
        if (roleStr == null || roleStr.trim().isEmpty()) {
            logger.warn("UserDao: Empty role found, using DEVELOPER as default");
            return Role.DEVELOPER;
        }

        try {
            return Role.valueOf(roleStr.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            logger.warn("UserDao: Invalid role value: {}, using DEVELOPER as default", roleStr);
            return Role.DEVELOPER;
        }
    }
}
//
//    public void updateLastLogin(Long userId, String userType) throws SQLException {
//
//    }
//
//    public boolean usernameExists(String username) throws SQLException {
//
//    }
//
//    public Optional<User> findById(Long id) throws SQLException {
//
//    }
//
//    public Optional<User> getCurrentUser() {
//
//    }

