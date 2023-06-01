package com.stage.gestiondestock_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
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
