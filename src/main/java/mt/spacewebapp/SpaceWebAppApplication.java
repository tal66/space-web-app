package mt.spacewebapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication
@EnableCaching
public class SpaceWebAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpaceWebAppApplication.class, args);

	}

}
