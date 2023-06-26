package com.skillstorm.demo.models;

import javax.persistence.*;
import javax.validation.constraints.*;

import com.skillstorm.demo.dtos.GoalDto;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "goals")
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user;
	
	@Column
	private String userId;
	
    @NotBlank
    private String name;

    @Column
    private String description;

    @Column(name = "picture_url")
    private String pictureUrl;

    @Future
    @Column
    private LocalDate targetDate;

    @Positive
    @Column
    private double targetAmount;

    @Column
    private double savedAmount;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "updated_at")
    private LocalDate updatedAt;
    
    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDate.now();
    }

    public GoalDto toDto() {
    	return new GoalDto(id, userId, name, description, pictureUrl, targetDate, targetAmount, savedAmount, createdAt, updatedAt);
    }
	public Goal() {
		super();
	}

	public Goal(Long id, String userId, @NotBlank String name, String description, String pictureUrl,
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
	
	public Goal(GoalDto goalData) {
		this.id = goalData.getId();
		this.userId = goalData.getUserId();
		this.name = goalData.getName();
		this.description = goalData.getDescription();
		this.pictureUrl = goalData.getPictureUrl();
		this.targetDate = goalData.getTargetDate();
		this.targetAmount = goalData.getTargetAmount();
		this.savedAmount = goalData.getSavedAmount();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String userId() {
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

//	public LocalDateTime getCreatedAt() {
//		return createdAt;
//	}
//
//	public void setCreatedAt(LocalDateTime createdAt) {
//		this.createdAt = createdAt;
//	}
//
//	public LocalDateTime getUpdatedAt() {
//		return updatedAt;
//	}
//
//	public void setUpdatedAt(LocalDateTime updatedAt) {
//		this.updatedAt = updatedAt;
//	}

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
		Goal other = (Goal) obj;
		return Objects.equals(id, other.id);
	}

    

}

