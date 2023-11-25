package com.hamza.chat.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.hamza.chat.entities.Chat;
import com.hamza.chat.entities.Souche;

public interface ChatService {
	Chat saveChat(Chat c);
	Chat updateChat(Chat c);
	void deleteChat(Chat c);
	void deleteChatById(Long id);
	Chat getChat(Long id);
	List<Chat> getAllChats();
	Page<Chat> getAllChatParPage(int page, int size);
	List<Chat> findByNomChat(String nom);
	List<Chat> findByNomChatContains(String nom);
	List<Chat> findByNomPrixAdoption (String nom, Double prixAdoption);
	List<Chat> findBySouche (Souche souche);
	List<Chat> findBySoucheIdSou(Long id);
	List<Chat> findByOrderByNomChatAsc();
	List<Chat> trierChatsNomsprixAdoption ();
	List<Souche> getAllSouche();
	

}
