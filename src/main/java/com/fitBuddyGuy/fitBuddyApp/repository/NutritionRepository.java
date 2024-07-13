package com.fitBuddyGuy.fitBuddyApp.repository;

import com.fitBuddyGuy.fitBuddyApp.model.Nutrition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NutritionRepository extends JpaRepository<Nutrition, Integer> {




}
