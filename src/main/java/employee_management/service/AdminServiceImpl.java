package employee_management.service;

import employee_management.domain.dao.AdminDao;
import employee_management.domain.model.Admin;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private static final Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

    private final AdminDao adminDao;

    @Override
    public Optional<Admin> getCurrentAdmin() throws SQLException {
        logger.info("AdminService: Getting current admin");

        try {
            Optional<Admin> result = adminDao.getCurrentAdmin();

            if (result.isPresent()) {
                logger.info("AdminService: Successfully retrieved admin");
            } else {
                logger.info("AdminService: No admin found");
            }

            return result;
        } catch (SQLException e) {
            logger.error("AdminService: SQL error occurred while getting current admin: {}", e.getMessage(), e);
            throw e; // Re-throw to let controller handle it
        } catch (Exception e) {
            logger.error("AdminService: Unexpected error occurred while getting current admin: {}", e.getMessage(), e);
            throw new SQLException("Service error: " + e.getMessage(), e);
        }
    }
}
