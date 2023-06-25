package com.stage.gestiondestock_backend;

import com.stage.gestiondestock_backend.config.ApplicationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
@EnableConfigurationProperties({ApplicationProperties.class})
public class GestiondestockBackendApplication {
/*
*  public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
* */
	public static void main(String[] args) {
		SpringApplication.run(GestiondestockBackendApplication.class, args);
	}

}
