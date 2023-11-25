package com.hamza.chat.repos;
import org.springframework.data.jpa.repository.JpaRepository;
import com.hamza.chat.entities.Image;
public interface ImageRepository extends JpaRepository<Image , Long> {
}

