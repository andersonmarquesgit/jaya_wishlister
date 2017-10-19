package com.wishlister.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.wishlister.model.Venue;
import com.wishlister.service.VenueService;

@Scope("view")
@Controller
public class VenueBean {

	private List<Venue> listVenuesRecent;	
	private List<Venue> listVenuesWishlist;	
	private String accessToken;
	
	@Autowired
	private VenueService venueService;
	
	@Autowired
	private LoginBean loginBean;
	
	@PostConstruct
	public void init(){
		accessToken = loginBean.getAccessToken();
		searchVenuesRecent(accessToken);
		searchVenuesWishlist();
	}
	
	private void searchVenuesRecent(String accessToken) {
		listVenuesRecent = venueService.searchVenuesRecent(accessToken);
	}
	
	private void searchVenuesWishlist() {
		listVenuesWishlist = venueService.searchVenuesWishlist();
	}

	public void addFavorite(Venue venue) {
		venueService.addWishlist(venue);
		searchVenuesWishlist();
		RequestContext.getCurrentInstance().update("formVenueWishlist");
	}
	
	public void removeFavorite(Venue venue) {
		venueService.removeWishlist(venue);
		searchVenuesWishlist();
		RequestContext.getCurrentInstance().update("formVenueWishlist");
	}
	
	public List<Venue> getListVenuesRecent() {
		return listVenuesRecent;
	}
	
	public List<Venue> getListVenuesWishlist() {
		return listVenuesWishlist;
	}

}
