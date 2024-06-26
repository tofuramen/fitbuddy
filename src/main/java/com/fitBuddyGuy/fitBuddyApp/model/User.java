package com.fitBuddyGuy.fitBuddyApp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

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
    private String userName;

    @NotNull(message = "Please enter a valid email address.")
    @Email(message = "Not a valid email", regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            flags = Pattern.Flag.CASE_INSENSITIVE)
    @Column(name = "email")
    private String email;

    @NotNull
    @Column(name = "password")
    private String password;

//    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinTable(
//            name = "users",
//            joinColumns = @JoinColumn(name = "user_role", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "user_role"))
//    private ArrayList<BeanDefinitionDsl.Role> roles = new ArrayList<>();

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

    @ManyToMany
    @JoinTable(
            name = "friends_list",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id")
    )
    private Set<User> friendsList = new HashSet<>();

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
