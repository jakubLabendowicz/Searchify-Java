package bp.PAI_jwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@ComponentScan(basePackages = "bp.PAI_jwt")
@EnableJpaRepositories(basePackages = "bp.PAI_jwt.repository")
public class PaiTestjwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaiTestjwtApplication.class, args);
	}


	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api/tracks").allowedOrigins("http://localhost:3000");
			}
		};
	}

}
