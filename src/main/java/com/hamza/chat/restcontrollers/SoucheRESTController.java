package com.hamza.chat.restcontrollers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.hamza.chat.entities.Souche;
import com.hamza.chat.repos.SoucheRepository;
@RestController
@RequestMapping("/api/sou")
@CrossOrigin("*")
public class SoucheRESTController {
@Autowired
SoucheRepository soucheRepository;
@RequestMapping(method=RequestMethod.GET)
public List<Souche> getAllSouches()
{
return soucheRepository.findAll();
}
@RequestMapping(value="/{id}",method = RequestMethod.GET)
public Souche getSoucheById(@PathVariable("id") Long id) {
return soucheRepository.findById(id).get();
}
}
