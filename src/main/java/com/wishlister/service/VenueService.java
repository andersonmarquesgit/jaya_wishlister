package com.wishlister.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wishlister.model.Venue;
import com.wishlister.repository.VenueRepository;

@Service
public class VenueService {
	
	private static final Logger log = Logger.getLogger(VenueService.class);
	
	private static final String SIZE = "original";
	
	private static final String CLIENT_ID = "NJAGQQMCRVCL3KCTZHDQRDVAPHELDBRNZGC2IUTRL24QTUMI";
	private static final String CLIENT_SECRET = "W2MGLX2SILICB4S52HJ30UWEX13HZAWSO3H2C1FLSGWWNT5K";
	private static final String YYYYMMDD = "20171014";
	
	private List<Venue> listUrlPhotosVenue;
	
	public String accessToken;
	
	@Autowired
	private VenueRepository venueRepository;
	
	public List<Venue> searchVenuesRecent(String accessToken) {
		this.accessToken = accessToken;
		return addListUrlPhotosVenue();
	}
	
	private List<Venue> addListUrlPhotosVenue() {
		listUrlPhotosVenue = new ArrayList<>();
		JSONObject jsonResponse = (JSONObject) getVenuesUserlessAuth().get("response");
		JSONArray arrVenues = (JSONArray) jsonResponse.get("venues");
		Iterator<JSONObject> iterator = arrVenues.iterator();
        while (iterator.hasNext()) {
            JSONObject jsonVenue = iterator.next();
            Venue venue = new Venue();
            venue.setVenueId((String) jsonVenue.get("id"));
            venue.setName((String) jsonVenue.get("name"));
            listUrlPhotosVenue.addAll(getPhotoVenues(venue));
        }
        
        return listUrlPhotosVenue;
	}
	
	public List<Venue> getPhotoVenues(Venue venue) {
		List<Venue> listUrlPhotoVenue = new ArrayList<>();
		try {
			URL url = new URL("https://api.foursquare.com/v2/venues/" + venue.getVenueId() + "/photos"
					+"?oauth_token=" + accessToken 
					+"&v=" + YYYYMMDD);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			InputStream content = connection.getInputStream();
			JSONParser parser = new JSONParser();
			JSONObject jsonObject = (JSONObject)parser.parse(
					new InputStreamReader(content, "UTF-8"));
			JSONObject jsonResponse = (JSONObject) jsonObject.get("response");
			JSONObject jsonPhotos = (JSONObject) jsonResponse.get("photos");
			JSONArray items = (JSONArray) jsonPhotos.get("items");
			
			if(items != null) {
				Iterator<JSONObject> iterator = items.iterator();
				while (iterator.hasNext()) {
					JSONObject item = iterator.next();
					if(item.get("prefix") != null && item.get("suffix") != null) {
						Venue venueWithPhoto = new Venue();
						venueWithPhoto.setVenueId(venue.getVenueId());
						venueWithPhoto.setName(venue.getName());
						venueWithPhoto.setUrlPhoto(item.get("prefix") + SIZE + item.get("suffix"));
						listUrlPhotoVenue.add(venueWithPhoto);
					}
				}
			}
			
			return listUrlPhotoVenue;
			
		} catch (IOException | ParseException e) {
			log.info("Erro de captura dos dados: ", e);
			return new ArrayList<>();
		}
	}
	
	private JSONObject getVenuesUserlessAuth() {
		try {
			URL url = new URL("https://api.foursquare.com/v2/venues/search?ll=40.7,-74&client_id=" + CLIENT_ID 
					 + "&client_secret=" + CLIENT_SECRET
					 + "&v=" + YYYYMMDD);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			InputStream content = connection.getInputStream();
			JSONParser parser = new JSONParser();
			JSONObject jsonVenues = (JSONObject)parser.parse(
				      new InputStreamReader(content, "UTF-8"));
			return jsonVenues;
		} catch (IOException | ParseException e) {
			log.info("Erro de captura dos dados: ", e);
		}
		
		return null;
	}

	@Transactional
	public void addWishlist(Venue venue) {
		venueRepository.saveAndFlush(venue);
	}
	
	@Transactional
	public void removeWishlist(Venue venue) {
		venueRepository.delete(venue);
	}
	
	public List<Venue> searchVenuesWishlist() {
		return venueRepository.findAll();
	}
}
