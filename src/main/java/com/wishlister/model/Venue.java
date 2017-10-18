package com.wishlister.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_venue")
public class Venue {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
    private Long id;
	
	@Column(name="venue_id")
	private String venueId;
	
	@Column(name="name")
	private String name;
	
	@Column(name="url_photo")
	private String urlPhoto;
	
	@Column(name="user_name")
	private String userName;
	
	@Column(name="url_user_photo")
	private String urlUserPhoto;
	
	public String getVenueId() {
		return venueId;
	}
	
	public void setVenueId(String venueId) {
		this.venueId = venueId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getUrlPhoto() {
		return urlPhoto;
	}
	
	public void setUrlPhoto(String urlPhoto) {
		this.urlPhoto = urlPhoto;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getUrlUserPhoto() {
		return urlUserPhoto;
	}
	
	public void setUrlUserPhoto(String urlUserPhoto) {
		this.urlUserPhoto = urlUserPhoto;
	}

}
