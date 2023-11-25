package com.hamza.chat.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.hamza.chat.entities.Souche;
@RepositoryRestResource(path = "sou")
@CrossOrigin("http://localhost:4200/") //pour autoriser angular 
public interface SoucheRepository extends JpaRepository<Souche, Long> {

}
