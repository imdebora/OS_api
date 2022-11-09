package com.debora.os20.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.debora.os20.services.DBServices;

@Configuration
@Profile("test")
public class TestConfig {

	@Autowired
	private DBServices dbServices;

	@Bean
	public void instanciaDB() {

		this.dbServices.instanciaDB();

	}

}
