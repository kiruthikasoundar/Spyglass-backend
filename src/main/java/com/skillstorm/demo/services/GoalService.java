package com.skillstorm.demo.services;

import com.skillstorm.demo.dtos.GoalDto;
import com.skillstorm.demo.models.Goal;
import com.skillstorm.demo.repositories.GoalRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

@Service
public class GoalService {

    @Autowired
	private GoalRepository goalRepository;

	public List<GoalDto> getAllGoals() {
		return goalRepository.findAll()
				.stream()
				.map(Goal::toDto)
				.collect(Collectors.toList());
	}

	public List<GoalDto> getAllGoalsByUserId(String userId) {
		return goalRepository.findAllGoalsByUserId(userId)
				.stream()
				.map(Goal::toDto)
				.collect(Collectors.toList());
	}
	
    public GoalDto createGoal(GoalDto goalDto) {
        Goal goal = new Goal(goalDto);
        return goalRepository.save(goal).toDto();
    }

	public GoalDto updateGoal(long id, @Valid GoalDto goalDto) {
		Goal goal = new Goal(goalDto);
		goal.setId(id);
		return goalRepository.save(goal).toDto();
	}

    public void deleteGoal(Long id) {
    		goalRepository.deleteById(id);
    }

}
