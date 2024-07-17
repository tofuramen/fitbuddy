package com.fitBuddyGuy.fitBuddyApp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Table(name = "nutritiontracker")
@Entity
public class Nutrition {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "nutrition_gen")
    @SequenceGenerator(name = "nutrition_gen",
            sequenceName = "nutritiontracker_seq", allocationSize = 1)
    private int nutrition_id;


    @Column(name = "user_id")
    private int userID;

    @Column(name="entry_date")
    private LocalDateTime entryDate;


    @Column(name="carbs")
    private int carbs;

    @Column(name="protein")
    private int protein;

    @Column(name="fat")
    private int fat;

    @Column(name="current_weight")
    private int weight;

    @NotNull(message = "calories are required.")
    @Column(name="total_calories")
    private int total_calories;

    public Nutrition(int userID, LocalDateTime entryDate, int carbs, int fat, int protein, int total_calories) {
        this.userID = userID;
        this.entryDate = entryDate;
        this.carbs = carbs;
        this.fat = fat;
        this.protein = protein;
        this.total_calories = total_calories;
    }

    public Nutrition() {

    }

    public void setId(int nutrition_id) {
        this.nutrition_id = nutrition_id;
    }

    public int getId() {
        return nutrition_id;
    }

    public void setUser_id(int userID) {
        this.userID = userID;
    }

    public int getUser_id() {
        return this.userID;
    }

    public int user_id() {
        return this.userID;
    }


    public LocalDateTime getEntry_date() {
        return entryDate;
    }

    public void setEntry_date(LocalDateTime entry_date) {
        this.entryDate = entry_date;
    }

    public int getCarbs() {
        return carbs;
    }

    public void setCarbs(int carbs) {
        this.carbs = carbs;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public int getTotal_calories() {
        return total_calories;
    }

    public void setTotal_calories(int total_calories) {
        this.total_calories = total_calories;
    }

    public void addMeal(int protein, int carbs, int fat, int mealCalories ) {
        setProtein(getProtein() + protein);
        setCarbs(getCarbs() + carbs);
        setFat(getFat() + fat);
        setTotal_calories(getTotal_calories() + mealCalories);
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
