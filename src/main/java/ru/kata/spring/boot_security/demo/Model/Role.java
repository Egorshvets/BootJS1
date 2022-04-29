package ru.kata.spring.boot_security.demo.Model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private int roleId;

    @Column(name = "role")
    private String role;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users;



    public String getRole() {
        return role;
    }



//    public Set<SimpleGrantedAuthority> getAuthorities() {
//        return getPermissions().stream().map(authority
//                -> new SimpleGrantedAuthority(authority.getAuthority())).collect(Collectors.toSet());
//    }

    @Override
    public String toString() {
        return "" + role;
    }

    @Override
    public String getAuthority() {
        return role;
    }


}


