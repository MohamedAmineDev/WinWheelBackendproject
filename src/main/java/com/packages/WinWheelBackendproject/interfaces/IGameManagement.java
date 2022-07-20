package com.packages.WinWheelBackendproject.interfaces;

import com.packages.WinWheelBackendproject.models.Jeu;

import java.util.List;

public interface IGameManagement {
    Jeu getGameById(Long id);

    List<Jeu> getGamesByUserId(Long userId);

    boolean addAGame(Long userId, Jeu jeu);

    boolean updateAGame(Long id, Jeu jeu);


}
