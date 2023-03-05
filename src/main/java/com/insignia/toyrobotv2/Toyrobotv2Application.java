package com.insignia.toyrobotv2;

import com.insignia.toyrobotv2.controller.GameController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Toyrobotv2Application implements CommandLineRunner {

	@Autowired
	private GameController gameController;
	public static void main(String[] args) {

		SpringApplication.run(Toyrobotv2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		gameController.start();
	}
}
