package com.hamza.chat;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import com.hamza.chat.entities.Chat;
import com.hamza.chat.entities.Souche;
import com.hamza.chat.service.ChatService;

@SpringBootApplication
public class ChatApplication  implements CommandLineRunner  {
	@Autowired
	ChatService chatService;
	@Autowired
	private RepositoryRestConfiguration repositoryRestConfiguration;
	public static void main(String[] args) {
		SpringApplication.run(ChatApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
	repositoryRestConfiguration.exposeIdsFor(Chat.class,Souche.class);
	}
}
