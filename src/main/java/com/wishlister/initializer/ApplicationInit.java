package com.wishlister.initializer;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.wishlister.config.MvcConfig;
import com.wishlister.config.RepositoryConfig;
import com.wishlister.config.SecurityConfig;
import com.wishlister.config.ServiceConfig;

public class ApplicationInit extends
		AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { MvcConfig.class, SecurityConfig.class, RepositoryConfig.class, ServiceConfig.class, SecurityConfig.class };
	}

	// @Override
	// protected Class<?>[] getServletConfigClasses() {
	// return new Class[] {MvcConfig.class};
	// }
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
}
