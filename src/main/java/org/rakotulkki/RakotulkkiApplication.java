package org.rakotulkki;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.rakotulkki.orika.OrikaMapperFactoryBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class RakotulkkiApplication {

	private DefaultMapperFactory factory;

	public RakotulkkiApplication() {
		this.factory = new OrikaMapperFactoryBuilder().build();
	}

	public static void main(String[] args) {
		SpringApplication.run(RakotulkkiApplication.class, args);
	}

	@Bean
	MapperFacade mapperFacadeProvider() {
		return factory.getMapperFacade();
	}

}
