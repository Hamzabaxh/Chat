package com.hamza.chat.entities;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "nomChatl", types = { Chat.class })
public interface ChatProjection {
	public String getNomChat();
}
