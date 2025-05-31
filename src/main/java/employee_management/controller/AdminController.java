package employee_management.controller;

import employee_management.domain.model.Admin;
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

//@RestController
//@RequestMapping("/api/admin")
//@RequiredArgsConstructor
//public class AdminControllerImpl implements AdminController{
//    private final AdminService adminService;
//
//    @Override
//    @GetMapping("/getCurrentAdmin")
//    public ResponseEntity<Admin> getCurrentAdmin() {
//        try {
//            Optional<Admin> admin = adminService.getCurrentAdmin();
//            if (admin.isPresent()) {
//                return ResponseEntity.ok(admin.get());
//            } else {
//                return ResponseEntity.notFound().build();
//            }
//        } catch (SQLException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }
//}
@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    private final AdminService adminService;

    @GetMapping("/getCurrentAdmin")
    public ResponseEntity<?> getCurrentAdmin() {
        logger.info("AdminController: Received request to get current admin");

        try {
            Optional<Admin> admin = adminService.getCurrentAdmin();

            if (admin.isPresent()) {
                logger.info("AdminController: Successfully returning admin data");
                return ResponseEntity.ok(admin.get());
            } else {
                logger.info("AdminController: No admin found, returning 404");
                return ResponseEntity.notFound().build();
            }

        } catch (SQLException e) {
            logger.error("AdminController: Database error occurred: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of(
                            "error", "Database error",
                            "message", e.getMessage(),
                            "timestamp", LocalDateTime.now()
                    ));

        } catch (Exception e) {
            logger.error("AdminController: Unexpected error occurred: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of(
                            "error", "Internal server error",
                            "message", e.getMessage(),
                            "timestamp", LocalDateTime.now()
                    ));
        }
    }
}