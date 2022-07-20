package com.packages.WinWheelBackendproject.interfaces;

import com.packages.WinWheelBackendproject.models.Essai;

public interface IPlayManagement {
    boolean alreadyPlayedTheGame(Long playerId, Long gameId);

    boolean playTheGame(Long playerId, Long gameId, Essai essai);
}
