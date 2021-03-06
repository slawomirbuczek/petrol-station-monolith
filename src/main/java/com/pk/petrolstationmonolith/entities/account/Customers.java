package com.pk.petrolstationmonolith.entities.account;

import com.pk.petrolstationmonolith.enums.Roles;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;

import static java.util.Collections.singletonList;

@Entity
@Table(name = "Customers")
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Customers implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "Password")
    private String password;

    @Column(name = "Email")
    private String email;

    @Column(name = "Role")
    @Enumerated(EnumType.STRING)
    private Roles role;

    @Column(name = "Enabled")
    private Boolean enabled;

    public Customers(String password, String email, Roles role) {
        this.password = password;
        this.email = email;
        this.role = role;
        this.enabled = false;
    }

    public Customers(Long id, String password, String email, Roles role) {
        this.id = id;
        this.password = password;
        this.email = email;
        this.role = role;
        this.enabled = true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return singletonList(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public String getUsername() {
        return String.valueOf(id);
    }

    @Override
    public String getPassword() {
        return password;
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
        return enabled;
    }

}
