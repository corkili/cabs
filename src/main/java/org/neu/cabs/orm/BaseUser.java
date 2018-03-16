package org.neu.cabs.orm;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

/**
 * 基用户实体类，用户基类
 * @author 李浩然
 * @see org.springframework.security.core.userdetails.UserDetails
 */
@Entity
@Table(name = "USERS")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "D_TYPE")
@Setter
@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseUser implements UserDetails{

    /**
     * 用户ID
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户名
     */
    @Column(unique = true, length = 50)
    @NotNull
    private String username;

    /**
     * 密码
     */
    @Column(length = 128)
    @NotNull
    private String password;

    /**
     * 创建时间
     */
    @Column(updatable = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    @NotNull
    private Date createTime;

    /**
     * 上次登录时间
     */
    @Column
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date lastLoginTime;

    /**
     * 是否可用
     */
    @Column
    @NotNull
    private boolean available;

    /**
     * 用户角色表
     */
    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.EAGER)
    private Set<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return available;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }
}
