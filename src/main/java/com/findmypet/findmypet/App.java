package com.findmypet.findmypet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "API Find My Pet", version = "v1", description = "API do Sistema de resgate de PETs em desastres", contact = @Contact(name = "Pedro Novais", url = "https://www.linkedin.com/in/pedroonovais/")))
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}
