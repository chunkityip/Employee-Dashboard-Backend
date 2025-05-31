package employee_management.domain.model;

import employee_management.domain.enums.Role;
import employee_management.domain.enums.entitlement.ManagerEntitlement;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;


@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Manager extends Employee {
    @Builder.Default
    private Role role = Role.MANAGER;
    @Builder.Default
    private Set<Long> teamMemberIds = new HashSet<>(); // or Set<Employee>

    private String department;

    // Manager-specific capabilities
    // Initialize entitlements to avoid NullPointerException
    @Builder.Default
    private EnumSet<ManagerEntitlement> entitlements = EnumSet.noneOf(ManagerEntitlement.class);
}
