package com.packages.WinWheelBackendproject.services;

import com.packages.WinWheelBackendproject.interfaces.ISelectionManagement;
import com.packages.WinWheelBackendproject.models.Jeu;
import com.packages.WinWheelBackendproject.models.Selection;
import com.packages.WinWheelBackendproject.repositories.GameRepository;
import com.packages.WinWheelBackendproject.repositories.GiftRepository;
import com.packages.WinWheelBackendproject.repositories.SelectionRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class SelectionService implements ISelectionManagement {
    @Autowired
    private SelectionRepository selectionRepository;
    @Autowired
    private GameRepository gameRepository;

    @Override
    public Selection getSelectionById(Long id) {
        return selectionRepository.findById(id).orElse(null);
    }

    @Override
    public List<Selection> getAllSelections() {
        Iterable<Selection> selectionIterable = selectionRepository.findAll();
        List<Selection> selections = new ArrayList<>();
        selectionIterable.forEach(selection -> selections.add(selection));
        return selections;
    }

    @Override
    public boolean addASelection(Selection selection, Long gameId) {
        try {
            Jeu game = gameRepository.findById(gameId).orElse(null);
            game.setSelection(selection);
            selection.setJeu(game);
            return selectionRepository.save(selection) != null && gameRepository.save(game) != null;
        } catch (Exception exception) {
            return false;
        }
    }

    @Override
    public boolean setTheGameOfASelection(Long idSelection, Long gameId) {
        try {
            Selection selection = getSelectionById(idSelection);
            Jeu game = gameRepository.findById(gameId).orElse(null);
            //System.out.println(game.getNom());

            Jeu oldGame = null;
            if (selection.getJeu() != null) {
                oldGame = selection.getJeu();
                oldGame.setSelection(null);
                gameRepository.save(oldGame);
            }
            selection.setJeu(game);
            game.setSelection(selection);
            return selectionRepository.save(selection) != null && gameRepository.save(game) != null;
        } catch (Exception exception) {
            System.out.println(exception.getStackTrace());
            return false;
        }
    }
}
