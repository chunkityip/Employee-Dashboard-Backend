package employee_management.controller;

import employee_management.domain.dao.auth.AuthResponse;
import org.springframework.http.ResponseEntity;

public interface AdminController {
        ResponseEntity<AuthResponse> getCurrentAdmin();
        ResponseEntity<AuthResponse> getAllAdmins();
        ResponseEntity<AuthResponse> getAdminById(Long id);
        ResponseEntity<AuthResponse> createAdmin(Object adminRequest);
        ResponseEntity<AuthResponse> updateAdmin(Long id, Object adminRequest);
        ResponseEntity<AuthResponse> deleteAdmin(Long id);
    }

}
