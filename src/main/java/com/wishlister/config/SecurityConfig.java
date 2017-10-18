package com.wishlister.config;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.wishlister.security.UserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan("com.wishlister.security")
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private static final Logger log = Logger.getLogger(SecurityConfig.class);
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	public SecurityConfig() {
		log.info("::::Inicialização do Security Config::::");
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.userDetailsService(userDetailsService);
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.headers().disable()
			.csrf().disable()
			.authorizeRequests()
			.antMatchers("/pages/**").permitAll()
			.and()
			.formLogin()
				.loginPage("/login.faces")
				.loginProcessingUrl("/j_spring_security_check")
            .defaultSuccessUrl("/pages/venues.xhtml")
		.and()
			.logout()   //logout configuration
			.logoutUrl("/") 
			.logoutSuccessUrl("/");
	}

	private AuthenticationFailureHandler customFailureHandler() {
		return (request, response, exception) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
	}

	private AuthenticationSuccessHandler customSuccessHandler() {
		return (request, response, authentication) -> response.sendError(HttpServletResponse.SC_OK);
	}

	private LogoutSuccessHandler customLogoutSuccessHandler() {
		return (request, response, authentication) -> response.sendError(HttpServletResponse.SC_OK);
	}

}