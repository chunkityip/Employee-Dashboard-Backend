package employee_management.domain.dao;

import employee_management.domain.enums.Role;
import employee_management.domain.enums.entitlement.AdminEntitlement;
import employee_management.domain.model.Admin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.EnumSet;
import java.util.Optional;

@Repository
public class AdminDao {

    private static final Logger logger = LoggerFactory.getLogger(AdminDao.class);

    @Autowired
    private DataSource dataSource;

    public Optional<Admin> getCurrentAdmin() throws SQLException {
        // Updated SQL to match the ACTUAL PostgreSQL column names from your logs
        String sql = "SELECT id, username, password, email, \"firstName\", \"lastName\", " +
                "\"createdAt\", \"updatedAt\", active, version, profile_image, " +
                "profile_image_filename, profile_image_content_type, role, entitlements " +
                "FROM admin LIMIT 1";

        logger.info("Executing query to get current admin: {}", sql);

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            logger.debug("Database connection established successfully");
            logger.debug("Connection URL: {}", connection.getMetaData().getURL());

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    logger.debug("Admin record found, building Admin object");

                    // Handle entitlements safely
                    EnumSet<AdminEntitlement> entitlements = EnumSet.noneOf(AdminEntitlement.class);
                    String entitlementsStr = resultSet.getString("entitlements");

                    if (entitlementsStr != null && !entitlementsStr.trim().isEmpty()) {
                        try {
                            String[] entitlementArray = entitlementsStr.split(",");
                            for (String entitlement : entitlementArray) {
                                String trimmedEntitlement = entitlement.trim();
                                if (!trimmedEntitlement.isEmpty()) {
                                    entitlements.add(AdminEntitlement.valueOf(trimmedEntitlement));
                                }
                            }
                            logger.debug("Parsed entitlements: {}", entitlements);
                        } catch (IllegalArgumentException e) {
                            logger.error("Invalid entitlement value found: {}", entitlementsStr, e);
                            // Continue with empty entitlements rather than failing
                        }
                    }

                    // Handle Role safely
                    Role role = Role.ADMIN; // default
                    String roleStr = resultSet.getString("role");
                    if (roleStr != null && !roleStr.trim().isEmpty()) {
                        try {
                            role = Role.valueOf(roleStr.trim());
                        } catch (IllegalArgumentException e) {
                            logger.warn("Invalid role value found: {}, using default ADMIN", roleStr, e);
                        }
                    }

                    // Handle PostgreSQL timestamp columns (using ACTUAL column names with quotes)
                    java.sql.Timestamp createdAtTs = resultSet.getTimestamp("createdAt");
                    java.sql.Timestamp updatedAtTs = resultSet.getTimestamp("updatedAt");

                    Admin admin = Admin.builder()
                            .id(resultSet.getLong("id"))
                            .username(resultSet.getString("username"))
                            .password(resultSet.getString("password"))
                            .email(resultSet.getString("email"))
                            // FIXED: Using actual PostgreSQL column names (camelCase with quotes)
                            .firstName(resultSet.getString("firstName"))     // Matches "firstName" in DB
                            .lastName(resultSet.getString("lastName"))       // Matches "lastName" in DB
                            .createdAt(createdAtTs != null ? createdAtTs.toLocalDateTime() : null)
                            .updatedAt(updatedAtTs != null ? updatedAtTs.toLocalDateTime() : null)
                            .active(resultSet.getBoolean("active"))
                            .version(resultSet.getLong("version"))
                            .profileImage(resultSet.getBytes("profile_image"))
                            .profileImageFilename(resultSet.getString("profile_image_filename"))
                            .profileImageContentType(resultSet.getString("profile_image_content_type"))
                            .role(role)
                            .entitlements(entitlements)
                            .build();

                    logger.info("Successfully retrieved admin: {} (ID: {}, Email: {})",
                            admin.getUsername(), admin.getId(), admin.getEmail());
                    logger.debug("Admin details - First Name: {}, Last Name: {}, Active: {}",
                            admin.getFirstName(), admin.getLastName(), admin.isActive());
                    return Optional.of(admin);
                } else {
                    logger.info("No admin record found in database");
                    return Optional.empty();
                }
            }
        } catch (SQLException e) {
            logger.error("SQLException occurred while getting current admin");
            logger.error("SQL State: {}, Error Code: {}", e.getSQLState(), e.getErrorCode());
            logger.error("Error Message: {}", e.getMessage(), e);
            throw new SQLException("Error getting current admin: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Unexpected error occurred while getting current admin: {}", e.getMessage(), e);
            throw new SQLException("Unexpected error getting current admin: " + e.getMessage(), e);
        }
    }


}