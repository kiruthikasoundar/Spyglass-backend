package com.skillstorm.demo.dtos;

import java.util.Objects;

import javax.validation.constraints.*;

public class UserDto {

    private Long id;

    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    // Constructors, getters, setters, and other methods

    /**
     * Constructs a new UserDto.
     */
    public UserDto() {
        super();
    }

    /**
     * Constructs a new UserDto with the specified parameters.
     *
     * @param id       the ID of the user
     * @param username the username of the user
     * @param email    the email of the user
     * @param password the password of the user
     */
    public UserDto(Long id, @NotBlank String name, @Email @NotBlank String email, @NotBlank String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setUsername(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
		UserDto other = (UserDto) obj;
		return Objects.equals(id, other.id);
	}

    
}
