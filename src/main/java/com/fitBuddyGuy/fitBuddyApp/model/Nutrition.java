package com.fitBuddyGuy.fitBuddyApp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Table(name = "nutritiontracker")
@Entity
public class Nutrition {

    @Id
    private int nutrition_id;


    @Column(name = "user_id")
    private int user_id;

    @Column(name="entry_date")
    private int entry_date;


    @Column(name="carbs")
    private int carbs;

    @Column(name="protein")
    private int protein;

    @Column(name="fat")
    private int fat;

    @NotNull(message = "calories are required.")
    @Column(name="total_calories")
    private int total_calories;

    public Nutrition(int user_id, int entry_date, int carbs, int fat, int protein, int total_calories) {
        this.user_id = user_id;
        this.entry_date = entry_date;
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

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int user_id() {
        return this.user_id;
    }


    public int getEntry_date() {
        return entry_date;
    }

    public void setEntry_date(int entry_date) {
        this.entry_date = entry_date;
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
}
