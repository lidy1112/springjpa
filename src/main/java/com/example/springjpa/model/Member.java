package com.example.springjpa.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@ManyToOne
	@JsonManagedReference    // 주인 반대 Entity 에 선언
	@JoinColumn(name = "team_id")
	private Team team;

	@Column(unique = true)
	private String name;
	private String password;
	private String email;
	private String token;

	public Member(String name, String password, String email) {
		this.name = name;
		this.password = password;
		this.email = email;
	}

	public void setMember(Team team, String name, String email) {
		this.team = team;
		this.name = name;
		this.email = email;
	}
}
