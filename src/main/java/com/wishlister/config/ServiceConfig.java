package com.wishlister.config;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.wishlister.service")
public class ServiceConfig {

	private static final Logger log = Logger.getLogger(ServiceConfig.class);

	public ServiceConfig() {
		log.info("::::Inicialização do Service Config e Escaneamento dos Serviços::::");
	}
	
}
