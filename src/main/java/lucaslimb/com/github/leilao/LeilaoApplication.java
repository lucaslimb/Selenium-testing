package lucaslimb.com.github.leilao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LeilaoApplication {

	// -Dspring.profiles.active=test
	public static void main(String[] args) {
		SpringApplication.run(LeilaoApplication.class, args);
	}

}
