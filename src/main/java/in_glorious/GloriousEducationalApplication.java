package in_glorious;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GloriousEducationalApplication {

	public static void main(String[] args) {
		SpringApplication.run(GloriousEducationalApplication.class, args);
	}

}
