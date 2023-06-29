//package com.skillstorm.demo.models;
//
//import javax.persistence.*;
//import javax.validation.constraints.*;
//
//import com.skillstorm.demo.dtos.UserDto;
//
//import java.time.LocalDateTime;
//import java.util.Objects;
//
//@Entity
//@Table(name = "users")
//public class User {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @NotBlank
//    @Column(unique = true)
//    private String name;
//
//    @Email
//    @NotBlank
//    @Column(unique = true)
//    private String email;
//
//    @NotBlank
//    private String password;
//
//    @Column(name = "created_at")
//    private LocalDateTime createdAt;
//
//    @Column(name = "updated_at")
//    private LocalDateTime updatedAt;
//
//	public User() {
//		super();
//	}
//
//	public User(Long id, @NotBlank String name, @Email @NotBlank String email, @NotBlank String password,
//			LocalDateTime createdAt, LocalDateTime updatedAt) {
//		super();
//		this.id = id;
//		this.name = name;
//		this.email = email;
//		this.password = password;
//		this.createdAt = createdAt;
//		this.updatedAt = updatedAt;
//	}
//
//    public User(UserDto userDto) {
//        this.name = userDto.getName();
//        this.email = userDto.getEmail();
//        this.password = userDto.getPassword();
//        
//    }
//    
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setUsername(String name) {
//		this.name = name;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}
//
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
//
//	@Override
//	public int hashCode() {
//		return Objects.hash(id);
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		User other = (User) obj;
//		return Objects.equals(id, other.id);
//	}
//
//    
//
//}
