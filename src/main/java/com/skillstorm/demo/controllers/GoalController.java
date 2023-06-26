package com.skillstorm.demo.controllers;

import com.skillstorm.demo.dtos.GoalDto;
import com.skillstorm.demo.services.GoalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("/goals")
@CrossOrigin(allowCredentials = "true", originPatterns = "http://localhost:5173")
public class GoalController {

	@Autowired
    private GoalService goalService;

    public GoalController(GoalService goalService) {
        this.goalService = goalService;
    }

    @GetMapping
    public List<GoalDto> getGoalsByUserId(@AuthenticationPrincipal OAuth2User user) {
    	String userId = (String) user.getAttributes().get("sub");
        return goalService.getAllGoalsByUserId(userId);     
    }

    @PostMapping
    public ResponseEntity<GoalDto> createGoal(@AuthenticationPrincipal OAuth2User user,@Valid @RequestBody GoalDto goalDto) {
    	String userId = (String) user.getAttributes().get("sub");
    	goalDto.setUserId(userId);
    	GoalDto createdGoal = goalService.createGoal(goalDto);
        return new ResponseEntity<>(createdGoal, HttpStatus.CREATED);
    }

    @PutMapping("/{goalId}")
    public GoalDto updateGoal(@PathVariable Long goalId,@Valid @RequestBody GoalDto goalDto) {
    	return goalService.updateGoal(goalId, goalDto);
    }

    @DeleteMapping("/{goalId}")
    public void deleteGoal(@PathVariable Long goalId) {
        goalService.deleteGoal(goalId);    
    }
}
