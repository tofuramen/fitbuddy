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
}
