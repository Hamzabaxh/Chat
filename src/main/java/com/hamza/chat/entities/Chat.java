package com.hamza.chat.entities;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.util.List;


import org.springframework.format.annotation.DateTimeFormat;
@Entity
public class Chat {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idChat;
	
	
	public String getCoulour() {
		return coulour;
	}
	public void setCoulour(String coulour) {
		this.coulour = coulour;
	}
	@NotNull
	@Size (min = 4,max = 15)
	private String nomChat;
	@Size (min = 4,max = 15)
	private String coulour;
	@Min(value = 10)
	 @Max(value = 10000)
	private Double prixAdoption;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@PastOrPresent
	private Date datenaissance;
	@ManyToOne
	private Souche souche;
	@OneToMany(mappedBy = "chat")
	 private List<Image> images;
	private String imagePath;
	
	public Chat() {
	super();
	}
	public Souche getSouche() {
		return souche;
	}
	public void setSouche(Souche souche) {
		this.souche = souche;
	}
	public Chat(String nomChat, Double prixAdoption, Date datenaissance) {
	this.nomChat = nomChat;
	this.prixAdoption = prixAdoption;
	this.datenaissance = datenaissance;
	}
	public Long getIdChat() {
		return idChat;
	}
	public void setIdChat(Long idChat) {
		this.idChat = idChat;
	}
	public String getNomChat() {
		return nomChat;
	}
	
	public void setNomChat(String nomChat) {
		this.nomChat = nomChat;
	}
	public Double getPrixAdoption() {
		return prixAdoption;
	}
	public void setPrixAdoption(Double prixAdoption) {
		this.prixAdoption = prixAdoption;
	}
	public Date getDatenaissance() {
		return datenaissance;
	}
	public void setDatenaissance(Date datenaissance) {
		this.datenaissance = datenaissance;
	}
	public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
	@Override
	public String toString() {
		return "Chat [idChat=" + idChat + ", nomChat=" + nomChat + ", coulour=" + coulour + ", prixAdoption="
				+ prixAdoption + ", datenaissance=" + datenaissance + ", souche=" + souche + "]";
	}
	
}
