package com.wishlister.controller;

import java.io.IOException;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.wishlister.service.LoginService;

@Scope("view")
@Controller
public class LoginBean {

	@Autowired
	private LoginService loginService;

	public void redirect() throws IOException {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		externalContext.redirect("https://pt.foursquare.com/oauth2/authenticate?client_id=NJAGQQMCRVCL3KCTZHDQRDVAPHELDBRNZGC2IUTRL24QTUMI&response_type=code&redirect_uri=http://localhost:3000/jaya-wishlister/pages/venues.faces");
	}

	public String getAccessToken() {
		return loginService.getAcessTokenByCode(getAuthenticationCode());
	}

	public String getAuthenticationCode() {
		return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("code");
	}
}
