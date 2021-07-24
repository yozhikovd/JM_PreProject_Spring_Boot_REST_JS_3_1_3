package com.yozhikovd.jm_preproject_spring_boot_3_1_1.models;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    @Length(min = 3, max = 30, message = "Name must be between 2 and 30 characters")
    private String name;

    @Column(name = "lastName")
    @Length(min = 3, max = 30, message = "LastName must be between 2 and 30 characters")
    private String lastName;

    @Min(value = 0)
    @Column(name = "age")
    private int age;

    @Column(name = "email")
    private String email;

    @UniqueElements
    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public String getUserRole(){
        boolean isAdmin = roles.stream().anyMatch(r -> r.role.equals("ROLE_ADMIN"));
        boolean isUser = roles.stream().anyMatch(r -> r.role.equals("ROLE_USER"));

        if (isAdmin && isUser){
            return "ROLE_ADMIN, ROLE_USER";
        }

        return isAdmin ? "ROLE_ADMIN" : "ROLE_USER";
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    public User(String name, String lastName, int age, String email, String username, String password, Set<Role> roles) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.username = username;
        this.password = password;
        this.roles = roles;
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
        return true;
    }

}
