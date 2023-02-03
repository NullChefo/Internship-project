package org.webserve.carservice.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//TODO create at the user service gRPC and call there
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    // TODO make unique = true
    @Column(length = 60, unique = true, nullable = false)
    private String email;
    @Column(length = 60, unique = true, nullable = false)
    private String username;
    @Column(length = 120)
    private String password;
    // TODO use roles
    private String roles;
    private boolean enabled = false; // email verified
    private boolean credentialsNonExpired = false;
    private boolean accountLocked = false;
    private boolean accountExpired = false;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.stream(StringUtils.trimAllWhitespace(roles).split(",")).map(SimpleGrantedAuthority::new).toList();
    }

    @Override
    public boolean isAccountNonExpired() {
        return !accountExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !accountLocked;
    }
}
