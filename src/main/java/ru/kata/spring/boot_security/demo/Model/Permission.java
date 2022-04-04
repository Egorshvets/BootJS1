package ru.kata.spring.boot_security.demo.Model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "permissions")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "permission")
    private String permission;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "role_permissions", joinColumns = @JoinColumn(name = "permission_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public Permission(String permission) {
        this.permission = permission;
    }

    public Permission() {
    }

    public String getPermission() {
        return permission;
    }

    @Override
    public String toString() {
        return permission;
    }
}
