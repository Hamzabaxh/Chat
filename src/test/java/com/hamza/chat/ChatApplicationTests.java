package com.hamza.chat;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import com.hamza.chat.entities.Souche;
import com.hamza.chat.entities.Chat;
import com.hamza.chat.repos.ChatRepository;
import com.hamza.chat.service.ChatService;

@SpringBootTest
class ChatApplicationTests {

	@Autowired
	private ChatRepository chatRepository;
	@Autowired
	private ChatService  chatService ;
	@Test
	public void testCreateChat() {
		Chat chat1 = new Chat("Sousou",50.500,new Date());
		chatRepository.save(chat1);
	}
	@Test
	public void testFindChat()
	{
		Chat c = chatRepository.findById(1L).get(); 
	System.out.println(c);
	}
	@Test
	public void testUpdateChat()
	{
		Chat c = chatRepository.findById(1L).get();
	c.setPrixAdoption(1000.0);
	chatRepository.save(c);
	}
	@Test
	public void testDeleteFreelancer()
	{
		chatRepository.deleteById(1L);;
	}
	@Test
	public void testListerTousChats()
	{
	List<Chat> chats = chatRepository.findAll();
	for (Chat c : chats)
	{
	System.out.println(c);
	}
	}
	@Test
	public void testFindBynomChatContains()
	{
	Page<Chat> chat1 = chatService.getAllChatParPage(0,3);
	System.out.println(chat1.getSize());
	System.out.println(chat1.getTotalElements());
	System.out.println(chat1.getTotalPages());
	chat1.getContent().forEach(c -> {System.out.println(c.toString());
	 });
	/*ou bien
	for (Produit p : prods)
	{
	System.out.println(p);
	} */
	}
	@Test
	public void testfindByNomChat()
	{
	List<Chat> chat1 = chatRepository.findByNomChat("Sousou");
	for (Chat c : chat1)
	{
	System.out.println(c);
	}
	}
	@Test
	public void testFindByNomChatContains()
	{
	List<Chat> chat1=chatRepository.findByNomChatContains("s");
	for (Chat c : chat1)
	{
	System.out.println(c);
	} }
	@Test
	public void testfindByNomPrixAdoption()
	{
	List<Chat> chat1 = chatRepository.findByNomPrixAdoption("Sousou", 50.0);
	for (Chat c : chat1)
	{
	System.out.println(c);
	}
	}

	@Test
	public void testfindBySouche()
	{
		Souche sou = new Souche();
	//sou.setIdSou(1L);
	List<Chat> chat1 = chatRepository.findBySouche(sou);
	for (Chat c : chat1)
	{
	System.out.println(c);
	}
	}
	
	@Test
	public void findBySoucheIdSou()
	{
	List<Chat> chat1 = chatRepository.findBySoucheIdSou(1L);
	for (Chat c : chat1)
	{
	System.out.println(c);
	}
	 }
	@Test
	public void testfindByOrderByNomChatAsc()
	{
	List<Chat> chat1 = chatRepository.findByOrderByNomChatAsc();
	for (Chat c : chat1)
	{
	System.out.println(c);
	}
	}
	@Test
	public void testTrierChatsNomsprixAdoption()
	{
	List<Chat> chat1 = chatRepository.trierChatsNomsPrixAdoption();
	for (Chat c : chat1)
	{
	System.out.println(c);
	}
	}

}
