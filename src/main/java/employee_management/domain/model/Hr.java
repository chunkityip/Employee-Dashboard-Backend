package employee_management.domain.model;


import employee_management.domain.enums.Role;
import employee_management.domain.enums.entitlement.HrEntitlement;
import employee_management.domain.enums.entitlement.ManagerEntitlement;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.EnumSet;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Hr extends User {
    @Builder.Default
    private Role role = Role.HR;

    private String department; // optional
    private String location;
    private String approvalSignature; // For digital approvals

    // HR-specific capabilities
    @Builder.Default
    private EnumSet<HrEntitlement> entitlements = EnumSet.noneOf(HrEntitlement.class);
}

