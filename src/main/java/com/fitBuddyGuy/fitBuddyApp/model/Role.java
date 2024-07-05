package com.fitBuddyGuy.fitBuddyApp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="users_roles")
public enum Role {
    USER, ADMIN;

    @Id
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
