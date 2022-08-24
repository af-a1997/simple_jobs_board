package com.afa1997.jobs_board.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "company")
public class Company {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cid;

	@NotBlank
	private String name;
	
	@Lob
	private String description;
	
	@NotBlank
	private String contact_email;
	
	@NotBlank
	private String building_addr;

	private String logo_filename;
	
	private String contact_phone_local;
	
	private String contact_phone_mob;

	// Getters and setters
	
	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
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

	public String getContact_email() {
		return contact_email;
	}

	public void setContact_email(String contact_email) {
		this.contact_email = contact_email;
	}
	
	public String getBuilding_addr() {
		return building_addr;
	}

	public void setBuilding_addr(String building_addr) {
		this.building_addr = building_addr;
	}

	public String getLogo_filename() {
		return logo_filename;
	}

	public void setLogo_filename(String logo_filename) {
		this.logo_filename = logo_filename;
	}

	public String getContact_phone_local() {
		return contact_phone_local;
	}

	public void setContact_phone_local(String contact_phone_local) {
		this.contact_phone_local = contact_phone_local;
	}

	public String getContact_phone_mob() {
		return contact_phone_mob;
	}

	public void setContact_phone_mob(String contact_phone_mob) {
		this.contact_phone_mob = contact_phone_mob;
	}
}
