package employee_management.domain.model;

import employee_management.domain.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DevOps extends Employee{
    private Role role = Role.DeVOpS;
}
