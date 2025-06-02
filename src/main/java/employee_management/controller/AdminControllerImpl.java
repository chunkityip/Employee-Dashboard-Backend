package employee_management.controller;

import employee_management.domain.dao.auth.AuthResponse;
import employee_management.domain.model.Admin;
import employee_management.domain.model.User;
import employee_management.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminControllerImpl implements AdminController {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    private final AdminService adminService;

    @GetMapping("/getCurrentAdmin")
    public ResponseEntity<AuthResponse> getCurrentAdmin() {
        logger.info("AdminController: Received request to get current admin");

        try {
            Optional<Admin> admin = adminService.getCurrentAdmin();  // ✅ User, not Admin

            if (admin.isPresent()) {
                logger.info("AdminController: Successfully returning admin data");

                // ✅ Create AuthResponse manually
                return ResponseEntity.ok(
                        AuthResponse.success("Admin retrieved successfully", admin.get())
                );
            } else {
                logger.info("AdminController: No admin found, returning 404");
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(AuthResponse.error("No admin found"));
            }

        } catch (SQLException e) {
            logger.error("AdminController: Database error occurred: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(AuthResponse.error("Database error: " + e.getMessage()));

        } catch (Exception e) {
            logger.error("AdminController: Unexpected error occurred: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(AuthResponse.error("Internal server error: " + e.getMessage()));
        }
    }

    @Override
    public ResponseEntity<AuthResponse> getAllAdmins() {
        return null;
    }

    @Override
    public ResponseEntity<AuthResponse> getAdminById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<AuthResponse> createAdmin(Object adminRequest) {
        return null;
    }

    @Override
    public ResponseEntity<AuthResponse> updateAdmin(Long id, Object adminRequest) {
        return null;
    }

    @Override
    public ResponseEntity<AuthResponse> deleteAdmin(Long id) {
        return null;
    }
}