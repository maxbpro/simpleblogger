package maxb.pro.simpleblogger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
@SpringBootApplication(exclude = org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class)
public class SimplebloggerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimplebloggerApplication.class, args);
	}
}
