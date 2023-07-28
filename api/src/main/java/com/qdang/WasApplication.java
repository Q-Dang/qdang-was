package com.qdang;

import com.qdang.application.ApplicationRoot;
import com.qdang.persistence.PersistenceRoot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication(scanBasePackageClasses = {
	WasApplication.class,
	ApplicationRoot.class,
	PersistenceRoot.class
})
@EnableJpaAuditing
public class WasApplication {

	public static void main(String[] args) {
		SpringApplication.run(WasApplication.class, args);
	}
}
