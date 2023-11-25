package com.hamza.chat.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hamza.chat.entities.Chat;
import com.hamza.chat.service.ChatService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ChatRESTController {
    @Autowired
    ChatService chatService;

    @GetMapping("/all")
    public List<Chat> getAllChats() {
        return chatService.getAllChats();
    }

    @GetMapping("/getbyid/{id}")
    public Chat getChatById(@PathVariable("id") Long id) {
        return chatService.getChat(id);
    }

    @PostMapping("/addchat")
    public Chat createChat(@RequestBody Chat chat) {
        return chatService.saveChat(chat);
    }

    @PutMapping("/updatechat")
    public Chat updateChat(@RequestBody Chat chat) {
        return chatService.updateChat(chat);
    }

    @DeleteMapping("/delchat/{id}")
    public void deleteChat(@PathVariable("id") Long id) {
        chatService.deleteChatById(id);
    }

    @GetMapping("/chatsou/{idSou}")
    public List<Chat> getChatsBySouId(@PathVariable("idSou") Long idSou) {
        return chatService.findBySoucheIdSou(idSou);
    }

    @GetMapping("/chatsByName/{nom}")
    public List<Chat> findByNomChatContains(@PathVariable("nom") String nom) {
        return chatService.findByNomChatContains(nom);
    }
}
