package employee_management.domain.model;

import employee_management.domain.enums.DeveloperType;
import employee_management.domain.enums.Role;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Developer extends User{
    @Builder.Default
    private Role role = Role.DEVELOPER;

    private String programmingLanguage;
    private String framework;

    private String currentProject;

    // Developer type: FRONTEND, BACKEND, FULLSTACK
    private DeveloperType developerType;

}
