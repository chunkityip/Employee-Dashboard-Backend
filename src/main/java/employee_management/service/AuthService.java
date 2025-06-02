package employee_management.service;

import employee_management.domain.dao.auth.AuthResponse;
import employee_management.domain.dao.auth.LoginRequest;
import employee_management.domain.dao.auth.LoginResponse;

import java.sql.SQLException;

public interface AuthService {
    LoginResponse login(LoginRequest loginRequest) throws SQLException;
    boolean logout(String sessionToken) throws SQLException;
    boolean isValidSession(String sessionToken) throws SQLException;
    AuthResponse getCurrentUser(String sessionToken) throws SQLException;
    AuthResponse validateSession(String sessionToken) throws SQLException;

}
