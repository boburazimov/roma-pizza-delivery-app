package uz.crm.system.entity.catalogs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.crm.system.entity.enums.UserStatusEnum;
import uz.crm.system.entity.template.AbsEntity;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

/**
 * Пользователь в системе.
 * TODO нужно продумать, будем ли использовать только эту сущность для входа в систему или еще другой.
 **/

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
public class User extends AbsEntity implements UserDetails {

    @Column(nullable = false)
    private String fullName;

    @ManyToOne(optional = false)
    private Restaurant restaurant;

    @Column(nullable = false, unique = true, length = 13)
    private String phoneNumber;

    @Column(nullable = false)
    private String password;

    @ManyToOne
    private Post post;

    @Enumerated(value = EnumType.STRING)
    private UserStatusEnum status;

    @Column(nullable = false, length = 4)
    private Integer pinCode;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role", joinColumns = {
            @JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private List<Role> roles;

    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return this.phoneNumber;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
