package employee_management.domain.model;

import employee_management.domain.enums.Role;
import employee_management.domain.enums.entitlement.AdminEntitlement;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.EnumSet;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Admin extends User{
    // Admin-specific capabilities
    // Initialize entitlements to avoid NullPointerException
    @Builder.Default
    private EnumSet<AdminEntitlement> entitlements = EnumSet.noneOf(AdminEntitlement.class);
}
