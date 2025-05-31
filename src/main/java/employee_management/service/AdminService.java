package employee_management.service;

import employee_management.domain.model.Admin;

import java.sql.SQLException;
import java.util.Optional;

public interface AdminService {
    Optional<Admin> getCurrentAdmin() throws SQLException;
}
