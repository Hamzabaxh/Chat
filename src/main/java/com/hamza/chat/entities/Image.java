package com.hamza.chat.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import java.util.Arrays;
import java.util.Objects;

@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idImage;
    private String name;
    private String type;

    public Chat getChat() {
		return chat;
	}

	public void setChat(Chat chat) {
		this.chat = chat;
	}

	@Column(name = "IMAGE", length = 4048576)
    @Lob
    private byte[] image;

    @ManyToOne()
    @JoinColumn (name="PRODUIT_ID")
    @JsonIgnore
    private Chat chat; 

    public Long getIdImage() {
		return idImage;
	}

	public void setIdImage(Long idImage) {
		this.idImage = idImage;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	// Constructors
    public Image() {
    }

    public Image(Long idImage, String name, String type, byte[] image, Chat chat) {
        this.idImage = idImage;
        this.name = name;
        this.type = type;
        this.image = image;
        this.chat = chat;
    }

    // Getters and setters
    // ... [Getters and setters go here]

    // equals, hashCode and toString
    // ... [equals, hashCode and toString methods go here]

    // Builder
    public static ImageBuilder builder() {
        return new ImageBuilder();
    }

    public static class ImageBuilder {
        private Long idImage;
        private String name;
        private String type;
        private byte[] image;
        private Chat chat;

        ImageBuilder() {
        }

        public ImageBuilder idImage(Long idImage) {
            this.idImage = idImage;
            return this;
        }

        public ImageBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ImageBuilder type(String type) {
            this.type = type;
            return this;
        }

        public ImageBuilder image(byte[] image) {
            this.image = image;
            return this;
        }

        public ImageBuilder chat(Chat chat) {
            this.chat = chat;
            return this;
        }

        public Image build() {
            return new Image(idImage, name, type, image, chat);
        }
    }
}
