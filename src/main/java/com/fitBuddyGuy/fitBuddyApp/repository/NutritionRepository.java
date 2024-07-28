package com.fitBuddyGuy.fitBuddyApp.repository;

import com.fitBuddyGuy.fitBuddyApp.model.Nutrition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface NutritionRepository extends JpaRepository<Nutrition, Integer> {


    Nutrition findByEntryDateAndUserID(@Param("entry_date") LocalDateTime dateTime,
                                         @Param("user_id") int user_id);


    //create multiple sql queries. First is to track user's weight over time. For the last 180 days.


    //Second is to see if user meets calorie goal. This can be a boolean added to each day's entry.
}
