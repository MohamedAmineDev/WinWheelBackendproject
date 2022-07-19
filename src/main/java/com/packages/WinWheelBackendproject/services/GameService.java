package com.packages.WinWheelBackendproject.services;

import com.packages.WinWheelBackendproject.interfaces.IGameManagement;
import com.packages.WinWheelBackendproject.models.Jeu;
import com.packages.WinWheelBackendproject.models.Utilisateur;
import com.packages.WinWheelBackendproject.repositories.GameRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class GameService implements IGameManagement {
    @Autowired
    private GameRepository gameRepository;

    @Override
    public Jeu getGameById(Long id) {
        return gameRepository.findById(id).orElse(null);
    }

    @Override
    public List<Jeu> getGamesByUserId(Long userId) {
        return gameRepository.findByAdminId(userId);
    }

    @Override
    public boolean addAGame(Long userId, Jeu jeu) {
        try {
            Utilisateur utilisateur = new Utilisateur(userId);
            jeu.setAdmin(utilisateur);
            return gameRepository.save(jeu) != null;
        } catch (Exception exception) {
            return false;
        }
    }

    @Override
    public boolean updateAGame(Long id, Jeu jeu) {
        try {
            Jeu sameGame = getGameById(id);
            if (jeu.getNom() != null && jeu.getNom() != "" && jeu.getNom() != sameGame.getNom()) {
                sameGame.setNom(jeu.getNom());
            }
            if (jeu.getDescription() != null && jeu.getDescription() != "" && jeu.getDescription() != sameGame.getDescription()) {
                sameGame.setDescription(jeu.getDescription());
            }
            return gameRepository.save(sameGame) != null;
        } catch (Exception exception) {
            return false;
        }
    }
}
