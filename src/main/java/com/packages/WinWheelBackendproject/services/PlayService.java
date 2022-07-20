package com.packages.WinWheelBackendproject.services;

import com.packages.WinWheelBackendproject.interfaces.IPlayManagement;
import com.packages.WinWheelBackendproject.models.Essai;
import com.packages.WinWheelBackendproject.models.Jeu;
import com.packages.WinWheelBackendproject.models.Selection;
import com.packages.WinWheelBackendproject.models.Utilisateur;
import com.packages.WinWheelBackendproject.repositories.GameRepository;
import com.packages.WinWheelBackendproject.repositories.PlayRepository;
import com.packages.WinWheelBackendproject.repositories.SelectionRepository;
import com.packages.WinWheelBackendproject.repositories.UtilisateurRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class PlayService implements IPlayManagement {
    @Autowired
    private PlayRepository playRepository;
    @Autowired
    private SelectionRepository selectionRepository;
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Override
    public boolean alreadyPlayedTheGame(Long playerId, Long gameId) {
        try {
            //return playRepository.findBySelectionIdAndPlayerId(selectionRepository.findByJeuId(gameId).getId(), playerId) != null;
            Selection selection = selectionRepository.findByJeuId(gameId);
            System.out.println("selection id : " + selection.getId());
            Essai essai = playRepository.findBySelectionIdAndByPlayerId(selection.getId(), playerId, LocalDate.now());
            System.out.println("Essai : \n" + essai);
            if (essai == null) {
                return false;
            } else {
                return true;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean playTheGame(Long playerId, Long gameId, Essai essai) {
        try {
            Utilisateur player = utilisateurRepository.findById(playerId).orElse(null);
            Selection selection = selectionRepository.findByJeuId(gameId);
            essai.setPlayer(player);
            essai.setSelection(selection);
            return playRepository.save(essai) != null;
        } catch (Exception exception) {
            return false;
        }

    }
}
