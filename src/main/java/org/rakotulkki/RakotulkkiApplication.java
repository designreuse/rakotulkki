package org.rakotulkki;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class RakotulkkiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RakotulkkiApplication.class, args);
	}
}
