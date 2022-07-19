package com.packages.WinWheelBackendproject.services;

import com.packages.WinWheelBackendproject.enums.Roles;
import com.packages.WinWheelBackendproject.interfaces.IUserManagement;
import com.packages.WinWheelBackendproject.models.Utilisateur;
import com.packages.WinWheelBackendproject.repositories.UtilisateurRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class PlayerService implements IUserManagement {
    @Autowired
    private UtilisateurRepository utilisateurRepository;


    @Override
    public List<Utilisateur> getAllUsers() {
        Iterable<Utilisateur> utilisateurIterable = utilisateurRepository.findAllPlayers();
        List<Utilisateur> utilisateurs = new ArrayList<>();
        utilisateurIterable.forEach(utilisateur -> utilisateurs.add(utilisateur));
        return utilisateurs;
    }

    @Override
    public boolean addAUser(Utilisateur utilisateur) {
        utilisateur.setRole(Roles.ROLE_PLAYER.name());
        return utilisateurRepository.save(utilisateur) != null;
    }

}
