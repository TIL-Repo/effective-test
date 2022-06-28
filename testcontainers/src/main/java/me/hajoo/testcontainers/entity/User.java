package me.hajoo.testcontainers.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;

@Entity
@Getter
public class User {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String username;
	private String password;
	private String gender;
	private String state;
	private String useYn;
	private String birth;

	public User() {
	}

	public User(String username, String password, String gender, String state, String useYn, String birth) {
		this.username = username;
		this.password = password;
		this.gender = gender;
		this.state = state;
		this.useYn = useYn;
		this.birth = birth;
	}
}
