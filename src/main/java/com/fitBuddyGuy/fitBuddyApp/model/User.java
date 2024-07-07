package com.fitBuddyGuy.fitBuddyApp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Entity
@Table(name = "users")
public class User implements UserDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_gen")
    @SequenceGenerator(name = "user_gen",
            sequenceName = "users_seq", allocationSize = 1)
    private Integer id;

    @NotNull(message = "Name is required")
    @Size(min = 2, message = "is required")
    @Column(name = "firstname")
    private String firstName;

    @Size(message = "Username should be at least 5 characters")
    @NotNull(message = "Username is required")
    @Column(name = "username")
    private String username;

    @NotNull(message = "Please enter a valid email address.")
    @Email(message = "Not a valid email", regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            flags = Pattern.Flag.CASE_INSENSITIVE)
    @Column(name = "email")
    private String email;

    @NotNull
    @Column(name = "password")
    private String password;

    @Min(16)
    @NotNull(message = "Please enter an age above 16.")
    @Column(name = "age")
    private int age;

    @Min(24)
    @NotNull(message = "Please enter a height above 24 inches.")
    @Column(name = "height")
    private int height;

    @NotNull(message = "You must select a gender for calorie assignment")
    @Column(name = "gender")
    private String gender;

    @NotNull(message = "Are you so lazy, you don't even wanna pick activity level?")
    @Column(name = "activitylevel")
    private String activityLevel;

    @Min(85)
    @NotNull(message = "You need to enter a weight above 85 pounds.")
    @Column(name = "weight")
    private int weight;


    @Column(name="user_role")
    @Enumerated
    private Role role;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        User other = (User) o;
        return id != null && id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public User() {

    }

    public String getusername() {
        return username;
    }

    public void setusername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public void setActivityLevel(@NotNull(message = "Are you so lazy, you don't even wanna pick activity level?") String activityLevel) {
        this.activityLevel = activityLevel;
    }

    public String getActivityLevel() {
        return activityLevel;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
