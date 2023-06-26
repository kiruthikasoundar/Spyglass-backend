package com.skillstorm.demo.repositories;

import com.skillstorm.demo.models.Goal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GoalRepository extends JpaRepository<Goal, Long> {
    
    @Query("SELECT g FROM Goal g WHERE g.userId = :userId")
    List<Goal> findAllGoalsByUserId(String userId);
}
