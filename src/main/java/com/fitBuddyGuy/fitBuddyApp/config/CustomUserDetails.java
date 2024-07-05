package com.fitBuddyGuy.fitBuddyApp.config;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fitBuddyGuy.fitBuddyApp.model.Role;
import com.fitBuddyGuy.fitBuddyApp.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;


public class CustomUserDetails implements UserDetails {

    private User user;
    private int id;

    private String username;

    Role user_role;

    private String email;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(User user) {

    }


    public CustomUserDetails(int id, String getusername, String role, String password, String email, List<GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.user_role = Role.valueOf("USER");
        this.password = password;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getRoles().toString());
        return Arrays.asList(authority);
    }

    public String getPassword() {
        return user.getPassword();
    }

    public String getUsername() {
        return user.getusername();
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
}
