package com.packages.WinWheelBackendproject.repositories;

import com.packages.WinWheelBackendproject.models.Utilisateur;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UtilisateurRepository extends CrudRepository<Utilisateur, Long> {
    Utilisateur findByUsername(String username);

    Utilisateur findByEmail(String email);

    @Query("select u from Utilisateur u where role='ROLE_PLAYER'")
    List<Utilisateur> findAllPlayers();

    @Query("select u from Utilisateur u where role='ROLE_ADMIN'")
    List<Utilisateur> findAllAdmins();

    @Query("select u from Utilisateur u where u.email=:email and u.password=:password")
    Utilisateur findByEmailAndByPassword(String email, String password);

    Utilisateur findByEmailAndPassword(String email, String password);
}
