package employee_management.domain.model;

import employee_management.domain.enums.Role;
import employee_management.domain.enums.entitlement.ManagerEntitlement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Manager extends Employee {

    private Role role = Role.MANAGER;

    private Set<Long> teamMemberIds = new HashSet<>(); // or Set<Employee>
    private String department;

    // Manager-specific capabilities
    // Initialize entitlements to avoid NullPointerException
    private EnumSet<ManagerEntitlement> entitlements = EnumSet.noneOf(ManagerEntitlement.class);
}
