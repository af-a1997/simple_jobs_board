package com.afa1997.jobs_board.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "registered_users")
public class RegisteredUsers {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ruid;
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String surname;
	
	@NotBlank
	private String username;
	
	@NotBlank
	private String email;
	
	@NotBlank
	private String password;
	
	@Lob
	private String biography;
	
	private String home_addr;
	
	private String home_phone;
	
	private String mob_phone;
	
	private String profpic_filename;
	
	private boolean available_to_work;
	
	// Getters and setters

	public int getRuid() {
		return ruid;
	}

	public void setRuid(int ruid) {
		this.ruid = ruid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public String getHome_addr() {
		return home_addr;
	}

	public void setHome_addr(String home_addr) {
		this.home_addr = home_addr;
	}

	public String getHome_phone() {
		return home_phone;
	}

	public void setHome_phone(String home_phone) {
		this.home_phone = home_phone;
	}

	public String getMob_phone() {
		return mob_phone;
	}

	public void setMob_phone(String mob_phone) {
		this.mob_phone = mob_phone;
	}

	public String getProfpic_filename() {
		return profpic_filename;
	}

	public void setProfpic_filename(String profpic_filename) {
		this.profpic_filename = profpic_filename;
	}

	public boolean isAvailable_to_work() {
		return available_to_work;
	}

	public void setAvailable_to_work(boolean available_to_work) {
		this.available_to_work = available_to_work;
	}
}
