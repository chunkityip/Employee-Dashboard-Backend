package employee_management.domain.dao;

import employee_management.domain.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.Optional;

@Repository
public class AdminDao {
    @Autowired
    private DataSource dataSource;
    @Autowired
    public Optional<Admin> getCurrentAdmin() throws SQLException {
        // Select everything from the admin table
        String sql = "SELECT firstName , lastName , email , active , entitlements FROM admin ";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                Admin admin = Admin.builder()
                        .firstName(resultSet.getString("firstName"))
                        .lastName(resultSet.getString("lastName"))
                        .email(resultSet.getString("email"))
                        .active(resultSet.getBoolean("active"))
                        .entitlements(
                                EnumSet.copyOf(
                                        Arrays.stream(resultSet.getString("entitlements").split(","))
                                                .map(String::trim)
                                                .map(employee_management.domain.enums.entitlement.AdminEntitlement::valueOf)
                                                .toList()
                                )
                        )
                        .build();
                return Optional.of(admin);
            }
        } catch (SQLException e) {
            throw new SQLException("Error getting current admin", e);
        }
        return Optional.empty();
    }

    public Admin updateAdmin(Admin admin) throws SQLException {
        String sql = "UPDATE admin SET firstName = ?, lastName = ?, email = ?, active = ?, profileImage = ?, entitlements = ? WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, admin.getFirstName());
            statement.setString(2, admin.getLastName());
            statement.setString(3, admin.getEmail());
            statement.setBoolean(4, admin.isActive());
            statement.setBytes(5, admin.getProfileImage());
            statement.setString(6, String.join(",", admin.getEntitlements().stream().map(Enum::name).toList()));
            statement.setLong(7, admin.getId());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated == 0) {
                throw new SQLException("No admin record updated — possibly wrong ID.");
            }
            return admin;

        } catch (SQLException e) {
            throw new SQLException("Error updating admin", e);
        }
    }


//    updateAdminPassword() {
//
//    }
}
