package com.wishlister.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
	
	private static final String CLIENT_ID = "NJAGQQMCRVCL3KCTZHDQRDVAPHELDBRNZGC2IUTRL24QTUMI";
	private static final String CLIENT_SECRET = "W2MGLX2SILICB4S52HJ30UWEX13HZAWSO3H2C1FLSGWWNT5K";
	private static final String YYYYMMDD = "20171018";
	private static final String REDIRECT_URI = "http://localhost:3000/jaya-wishlister/pages/venues.faces";
	
	private static final Logger log = Logger.getLogger(LoginService.class);
	
	private String accessToken;	
	
	public String getAcessTokenByCode(String code) {
		try {
			URL url = new URL("https://foursquare.com/oauth2/access_token?client_id=" + CLIENT_ID 
					+ "&client_secret=" + CLIENT_SECRET 
					+ "&grant_type=authorization_code"
					+ "&redirect_uri=" + REDIRECT_URI
					+ "&code=" + code);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			InputStream content = connection.getInputStream();
			JSONParser parser = new JSONParser();
			JSONObject jsonToken = (JSONObject)parser.parse(
				      new InputStreamReader(content, "UTF-8"));
			accessToken = (String) jsonToken.get("access_token");
			return accessToken;
		} catch (IOException | ParseException e) {
			log.info("Erro de captura dos dados: ", e);
		}
		
		return accessToken;
	}
	
	public JSONObject getAccessFoursquare() {
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

}
