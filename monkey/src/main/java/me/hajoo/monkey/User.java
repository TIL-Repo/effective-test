package me.hajoo.monkey;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class User {

	@NotNull
	private Long id;
	@NotBlank
	private String username;
	@NotBlank
	private String password;
	@NotBlank
	private String name;

	private String address;
	@NotNull
	private UserState state;
	@NotNull
	@Size(min = 8, max = 8)
	private String birth;
	@Size(max = 3)
	private List<@NotBlank @Size(max = 8) String> hobbies;

	private List<String> subjects;

	enum UserState {
		JOIN, DELETE
	}
}
