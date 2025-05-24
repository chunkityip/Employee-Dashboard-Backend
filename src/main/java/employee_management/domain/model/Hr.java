package employee_management.domain.model;


import employee_management.domain.enums.Role;
import employee_management.domain.enums.entitlement.HrEntitlement;
import employee_management.domain.enums.entitlement.ManagerEntitlement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.EnumSet;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Hr extends Employee {

    private Role role = Role.HR;

    private String department; // optional
    private String location;
    private String approvalSignature; // For digital approvals

    // HR-specific capabilities
    private EnumSet<HrEntitlement> entitlements = EnumSet.noneOf(HrEntitlement.class);
}

