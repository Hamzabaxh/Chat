package com.hamza.chat.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Souche {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private long idSou;
private String nomSou;
private String descriptionSou;

@OneToMany(mappedBy = "souche")
@JsonIgnore
private List<Chat> chats;

@Override
public String toString() {
	return "Souche [idSou=" + idSou + ", nomSou=" + nomSou + ", descriptionSou=" + descriptionSou + ", chats=" + chats
			+ "]";
}

public long getIdSou() {
	return idSou;
}

public void setIdSou(long idSou) {
	this.idSou = idSou;
}

public String getNomSou() {
	return nomSou;
}

public void setNomSou(String nomSou) {
	this.nomSou = nomSou;
}

public String getDescriptionSou() {
	return descriptionSou;
}

public void setDescriptionSou(String descriptionSou) {
	this.descriptionSou = descriptionSou;
}

public List<Chat> getChats() {
	return chats;
}

public void setChats(List<Chat> chats) {
	this.chats = chats;
}





}
