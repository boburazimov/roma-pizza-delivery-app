package uz.crm.system.entity.catalogs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import uz.crm.system.entity.enums.RoleNameEnum;

import javax.persistence.*;

/**
 * Роли сотрудников, пользователей в системе - для разграничение доступа к ресурсам и к данным.
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(value = EnumType.STRING)
    private RoleNameEnum name;

    @Override
    public String getAuthority() {
        return name.name();
    }
}
