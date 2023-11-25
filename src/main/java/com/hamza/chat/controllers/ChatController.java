package com.hamza.chat.controllers;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hamza.chat.entities.Chat;
import com.hamza.chat.entities.Souche;
import com.hamza.chat.service.ChatService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

@Controller
public class ChatController {
	@Autowired
	ChatService chatService;
	@RequestMapping("/showCreate")
	public String showCreate(ModelMap modelMap)
	{
		List<Souche> sous = chatService.getAllSouche();
		Chat chat1 = new Chat();
		Souche sou = new Souche ();

		if (!sous.isEmpty()) {
		    sou = sous.get(0);
		}

		chat1.setSouche(sou);
		modelMap.addAttribute("chat", new Chat());
		modelMap.addAttribute("mode", "new");
		modelMap.addAttribute("souches", sous);
		
		for (Souche s : sous) {
			System.out.println(s);
		}
		
		modelMap.addAttribute("page",0);
		return "formChat";
	}
	
	@RequestMapping("/saveChat")
	public String saveChat(@Valid Chat chat,  BindingResult bindingResult, @ModelAttribute("page") int pageFromPrevious,
		    @RequestParam (name="size", defaultValue = "3") int size,
		    RedirectAttributes redirectAttributes,ModelMap modelMap)
	{ int page;
		 if (bindingResult.hasErrors()) {
			 List<Souche> sous = chatService.getAllSouche();
			 modelMap.addAttribute("souche", sous);
			 modelMap.addAttribute("mode", "edit");
		        return "formChat";
		    }if (chat.getIdChat()==null) //adding
		        page = chatService.getAllChats().size()/size; // calculer le nbr de pages
		    else //updating
		        page = pageFromPrevious;
		    chatService.saveChat(chat);
		    redirectAttributes.addAttribute("page", page);
		    return "redirect:/listeChats";
	}
	@RequestMapping("/listeChats")
	public String listeChats(ModelMap modelMap ,@RequestParam (name="page",defaultValue = "0") int page,
			@RequestParam (name="size", defaultValue = "3") int size)

	{
		Page<Chat> chatl = chatService.getAllChatParPage(page, size);
		modelMap.addAttribute("chats", chatl);
		 modelMap.addAttribute("pages", new int[chatl.getTotalPages()]);
		modelMap.addAttribute("currentPage", page);
		return "listeChats";
	}
	@RequestMapping("/supprimerChat")
	public String supprimerChat(@RequestParam("id") Long id,
	 ModelMap modelMap ,@RequestParam (name="page",defaultValue = "0") int page,
	 @RequestParam (name="size", defaultValue = "3") int size)
	{ 
		chatService.deleteChatById(id);
		Page<Chat> chatl = chatService.getAllChatParPage(page, 
				size);
				modelMap.addAttribute("chats", chatl);
				modelMap.addAttribute("pages", new int[chatl.getTotalPages()]);
				modelMap.addAttribute("currentPage", page);
				modelMap.addAttribute("size", size);

	return "listeChats";
	}
	@RequestMapping("/modifierChat")
	public String editerChat(@RequestParam("id") Long id, @RequestParam("page") int page,ModelMap modelMap)
	{
		Chat c = chatService.getChat(id);
		List<Souche> sous = chatService.getAllSouche();
	modelMap.addAttribute("chat", c);
	modelMap.addAttribute("mode", "edit");
	modelMap.addAttribute("page",page);
	modelMap.addAttribute("souche", sous);
	return "formChat";
	}
	@RequestMapping("/updateChat")
	public String updateChat(@ModelAttribute("chat") Chat chat,
	@RequestParam("date") String date,ModelMap modelMap) throws ParseException 
	{
		//conversion de la date 
		 SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		 Date datenaissance = dateformat.parse(String.valueOf(date));
		 chat.setDatenaissance(datenaissance);
		 
		 chatService.updateChat(chat);
		 List<Chat> chatl = chatService.getAllChats();
		 modelMap.addAttribute("chats", chatl);
		return "listeChats";
		}
}
