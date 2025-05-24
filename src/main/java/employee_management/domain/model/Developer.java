package employee_management.domain.model;

import employee_management.domain.enums.DeveloperType;
import employee_management.domain.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Developer extends Employee{
    private Role role = Role.DEVELOPER;

    private String programmingLanguage;
    private String framework;

    private String currentProject;

    // Developer type: FRONTEND, BACKEND, FULLSTACK
    private DeveloperType developerType;

}
