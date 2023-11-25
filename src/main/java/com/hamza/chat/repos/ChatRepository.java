package com.hamza.chat.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.hamza.chat.entities.Chat;
import com.hamza.chat.entities.Souche;
@RepositoryRestResource(path = "rest")
public interface ChatRepository extends JpaRepository<Chat, Long> {
	List<Chat> findByNomChat(String nom);
	List<Chat> findByNomChatContains(String nom);
	@Query("select c from Chat c where c.nomChat like %:nom and c.prixAdoption > :prixAdoption")
	List<Chat> findByNomPrixAdoption (@Param("nom") String nom,@Param("prixAdoption") Double prixAdoption);
	@Query("select c from Chat c where c.souche = ?1")
	List<Chat> findBySouche (Souche souche);
	List<Chat> findBySoucheIdSou(Long id);
	List<Chat> findByOrderByNomChatAsc();
	@Query("select c from Chat c order by c.nomChat ASC, c.prixAdoption DESC")
	List<Chat> trierChatsNomsPrixAdoption ();

}
