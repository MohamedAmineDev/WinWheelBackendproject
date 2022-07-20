package com.packages.WinWheelBackendproject.services;

import com.packages.WinWheelBackendproject.interfaces.IPlayManagement;
import com.packages.WinWheelBackendproject.models.*;
import com.packages.WinWheelBackendproject.repositories.*;
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
    @Autowired
    private GiftRepository giftRepository;

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
            if (essai.getStatut() == "win") {
                Cadeau cadeau = giftRepository.findById(essai.getPrizeId()).orElse(null);
                if (cadeau.getStock() > 0) {
                    cadeau.setStock(cadeau.getStock() - 1);
                    giftRepository.save(cadeau);
                } else {
                    throw new RuntimeException("The prize is not longer available try playing again");
                }
            }
            return playRepository.save(essai) != null;
        } catch (Exception exception) {
            return false;
        }

    }
}
