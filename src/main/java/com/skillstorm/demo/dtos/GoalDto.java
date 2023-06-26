package com.skillstorm.demo.dtos;

import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.Objects;

public class GoalDto {

    private Long id;
    
    private String userId;
    
    @NotBlank
    private String name;

    private String description;

    private String pictureUrl;

    @Future
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate targetDate;

    @Positive
    private double targetAmount;

    private double savedAmount;

    private LocalDate createdAt;
    
    private LocalDate updatedAt;
    
	public GoalDto() {
		super();
	}

	public GoalDto(Long id, String userId, @NotBlank String name, String description, String pictureUrl,
			@FutureOrPresent LocalDate targetDate, @Positive double targetAmount, double savedAmount,
			LocalDate createdAt, LocalDate updatedAt) {
		super();
		this.id = id;
		this.userId = userId;
		this.name = name;
		this.description = description;
		this.pictureUrl = pictureUrl;
		this.targetDate = targetDate;
		this.targetAmount = targetAmount;
		this.savedAmount = savedAmount;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public LocalDate getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(LocalDate targetDate) {
		this.targetDate = targetDate;
	}

	public double getTargetAmount() {
		return targetAmount;
	}

	public void setTargetAmount(double targetAmount) {
		this.targetAmount = targetAmount;
	}

	public double getSavedAmount() {
		return savedAmount;
	}

	public void setSavedAmount(double savedAmount) {
		this.savedAmount = savedAmount;
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDate getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDate updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GoalDto other = (GoalDto) obj;
		return Objects.equals(id, other.id);
	}
    
    
}
