package com.johanekstroem.parking.Config;

import org.geolatte.geom.json.GeolatteGeomModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfiguration {

  @Bean
	GeolatteGeomModule geolatteModule(){
	return new GeolatteGeomModule();
	}

}
