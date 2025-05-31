package employee_management.domain.model;

import employee_management.domain.enums.Role;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Ba extends User{
    @Builder.Default
    private Role role = Role.BA;
}
